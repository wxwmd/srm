package com.jaezi.web.api;

import com.jaezi.common.base.BaseException;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.cache.IpUtils;
import com.jaezi.common.constant.UserConst;
import com.jaezi.common.util.FileUtil;
import com.jaezi.common.util.IpUtil;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.common.util.SHA256;
import com.jaezi.system.dto.SupplierDto;
import com.jaezi.system.model.User;
import com.jaezi.system.service.UserService;
import io.minio.MinioClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2020/10/16 14:14
 * @description 认证API
 */
@RestController
public class AuthenticationApi {

    private final UserService userService;

    private final MinioClient minioClient;

    public AuthenticationApi(UserService userService, MinioClient minioClient) {
        this.userService = userService;
        this.minioClient = minioClient;
    }

    /**
     * 用户登陆接口
     *
     * @param user     用户登陆信息
     * @param request  request
     * @param response response
     * @return 登陆是否成功
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        //根据用户名获取用户
        User u = userService.getUserByUsername(user.getUsername());
        if (null == u) {
            //登录失败，记录ip至缓存
            IpUtils.setIpListCache(IpUtil.getIpAddress(request));

            return new JsonResult(ResponseEnum.FAILURE_USER_NOT_EXIST);
        }
        //根据用户类型和用户名获取用户
        u = userService.getUser(user.getUsername(), u.getUserType());
        if (null == u) {
            //登录失败，记录ip至缓存
            IpUtils.setIpListCache(IpUtil.getIpAddress(request));

            return new JsonResult(ResponseEnum.FAILURE_USER_NOT_EXIST);
        }
        if (u.getStatus().equals(UserConst.LOCK)) {
            return new JsonResult(ResponseEnum.FAILURE_USER_LOCKED);
        }
        Integer examineStatus = Optional.ofNullable(u.getExamineStatus()).orElse(1);
        if (examineStatus == 0 || examineStatus == 2) {
            return new JsonResult(ResponseEnum.FAILURE.getCode(), "您的账号审核未通过,请联系管理员");
        }
        String password = userService.getPassword(u.getId());
        String tem=SHA256.hash(user.getPassword());
        if (password.equals(SHA256.hash(user.getPassword()))) {
            u.setPassword("");
            String token = JwtUtil.createToken(user.getUsername(), u.getId(), u.getRealName(), u.getUserType(), u.getQuota());
            u.setToken(token);
            response.addHeader("Credential", token);
            //如果最后登陆时间为空,将创建时间赋值给最后登陆时间
            Long lastTime = ObjectUtils.isEmpty(u.getLastLoginTime()) ? u.getCreateTime() : u.getLastLoginTime();
            u.setLastLoginTime(System.currentTimeMillis());
            //获取时间戳更新到用户表
            userService.update(u);
            u.setLastLoginTime(lastTime);
            u.setToken("");
            //登录成功后删除IP缓存
            IpUtils.deleteIpListCache(IpUtil.getIpAddress(request));
            return new JsonResult(u);
        } else {
            //登录失败，记录ip至缓存
            IpUtils.setIpListCache(IpUtil.getIpAddress(request));

            return new JsonResult(ResponseEnum.FAILURE_PASSWORD_WRONG);
        }
    }

    /**
     * 退出登陆的接口
     *
     * @param response response
     * @param request  request
     * @return 是否成功
     */
    @PostMapping("/logout")
    public JsonResult logout(HttpServletResponse response, HttpServletRequest request) {
        userService.removeToken(JwtUtil.getUserId(request));
        response.addHeader("Credential", "");
        return JsonResult.SUCCESS;
    }

    /**
     * 无权限访问接口
     *
     * @param code 响应代码
     * @return 返回原因
     */
    @RequestMapping(path = "/ac/{code}")
    public JsonResult unauthorized(@PathVariable Integer code) {
        switch (ResponseEnum.getByCode(code)) {
            case FAILURE_UNAUTHORIZED:
                return new JsonResult(ResponseEnum.FAILURE_UNAUTHORIZED);
            case FAILURE_SIGN_ERROR:
                return new JsonResult(ResponseEnum.FAILURE_SIGN_ERROR);
            case FAILURE_FORBIDDEN:
                return new JsonResult(ResponseEnum.FAILURE_FORBIDDEN);
            case FAILURE_REQUEST_TIMEOUT:
                return new JsonResult(ResponseEnum.FAILURE_REQUEST_TIMEOUT);
            case FAILURE_REPEAT_REQUEST:
                return new JsonResult(ResponseEnum.FAILURE_REPEAT_REQUEST);
            case FAILURE_PRECONDITION_FAILED:
                return new JsonResult(ResponseEnum.FAILURE_PRECONDITION_FAILED);
            case ACCOUNT_LOCK:
                return new JsonResult(ResponseEnum.ACCOUNT_LOCK);
            default:
                return new JsonResult(ResponseEnum.FAILURE);
        }
    }

    /**
     * 供应商注册
     *
     * @param registerInfo 注册info
     * @return JsonResult 是否删除成功
     */
    @PostMapping("/register")
    public JsonResult register(@RequestBody SupplierDto registerInfo) throws BaseException {
        return userService.register(registerInfo);
    }

    /**
     * 公共文件上传
     *
     * @param file 上传的文件对象
     * @return 文件的访问路径
     */
    @PostMapping("/upload")
    public JsonResult uploadProductImage(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty() || file.getSize() == 0) {
            return JsonResult.FAILURE;
        }
        Map<String, String> map = FileUtil.upload(file, minioClient);
        if (ObjectUtils.isEmpty(map)) {
            return JsonResult.FAILURE;
        }
        return new JsonResult(map);
    }

    /**
     * 公共多文件上传
     *
     * @param request 请求对象
     * @return 文件的访问路径集合
     */
    @PostMapping(value = "/upload/multi")
    public JsonResult uploadProductImages(StandardMultipartHttpServletRequest request) {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        if (fileMap.size() == 0) {
            return JsonResult.FAILURE;
        }
        List<Map<String, String>> result = new ArrayList<>();
        for (MultipartFile file : fileMap.values()) {
            Map<String, String> map = FileUtil.upload(file, minioClient);
            if (!ObjectUtils.isEmpty(map)) {
                result.add(map);
            }
        }
        return new JsonResult(result);
    }
}
