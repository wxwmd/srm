package com.jaezi.system.api;

import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.constant.UserConst;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.common.util.SHA256;
import com.jaezi.system.dto.UserDto;
import com.jaezi.system.model.Supplier;
import com.jaezi.system.model.User;
import com.jaezi.system.service.SupplierService;
import com.jaezi.system.service.UserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/16 17:12
 * @description 用户API
 */
@RestController
@RequestMapping("/sys/user")
public class UserApi {

    private final UserService userService;
    private final SupplierService supplierService;

    public UserApi(UserService userService, SupplierService supplierService) {
        this.userService = userService;
        this.supplierService = supplierService;
    }

    /**
     * 用户列表（分页）
     *
     * @param filter 分页对象
     * @return 对应列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        filter.put("isAdmin", String.valueOf(JwtUtil.getUserType()));
        return new JsonResult(userService.getAll(filter));
    }

    /**
     * 用户根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(userService.getOneById(id));
    }

    /**
     * 用户添加
     *
     * @param user 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody User user) {
        return returnIntResult(userService.add(user));
    }

    /**
     * 用户修改
     *
     * @param user 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody User user) {
        if (userService.update(user) != 0) {
            return JsonResult.SUCCESS;
        } else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 用户删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(userService.delete(id));
    }

    private JsonResult returnIntResult(int result) {
        if (result > 0) {
            return JsonResult.SUCCESS;
        } else if (result < 0) {
            return new JsonResult(ResponseEnum.FAILURE.getCode(), "用户已存在");
        } else {
            return JsonResult.FAILURE;
        }
    }

    private JsonResult returnObjectResult(Object obj) {
        return null == obj ? new JsonResult(ResponseEnum.SUCCESS_NO_CONTENT) : new JsonResult(obj);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @GetMapping("/name/{userName}")
    public JsonResult getUserByName(@PathVariable String userName) {
        User user = userService.getUserByUsername(userName);
        if (user == null) {
            return JsonResult.FAILURE;
        }
        user.setPassword(null);
        return new JsonResult(user);
    }

    /**
     * 根据角色Id获取用户信息
     *
     * @param roleId 角色Id
     * @return 用户信息
     */
    @GetMapping("/roleId/{roleId}")
    public JsonResult getUserByRoleId(@PathVariable Integer roleId) {
        List<User> userList = userService.getUserByRoleId(roleId);
        return new JsonResult(userList);
    }

    /**
     * 锁定用户
     *
     * @param user 用户
     * @return
     */
    @PutMapping("/lock")
    public JsonResult lockUser(@RequestBody User user) {
        user.setStatus(UserConst.LOCK);
        return update(user);
    }

    /**
     * 解锁用户
     *
     * @param user 用户
     * @return
     */
    @PutMapping("/unlock")
    public JsonResult unlockUser(@RequestBody User user) {
        user.setStatus(UserConst.NORMAL);
        return update(user);
    }

    /**
     * 重置用户密码
     *
     * @param id 用户id
     * @return
     */
    @PutMapping("/{id}/pwd")
    public JsonResult restPassword(@PathVariable Integer id) {
        User user = userService.getOneById(id);
        String newPassword = SHA256.hash(UserConst.RAW_PASSWORD);
        user.setPassword(newPassword);
        return update(user);
    }

    /**
     * 修改密码
     *
     * @param userDto
     * @param request
     * @return JsonResult
     * @author warren
     * @date 2021/7/21
     * @since 1.0
     */
    @PutMapping("/password")
    public JsonResult updatePassword(@RequestBody UserDto userDto, HttpServletRequest request) {
        String token = request.getHeader("Credential");
        Integer userId = JwtUtil.getUserId(token);
        if (!userDto.getUserId().equals(userId)) {
            return JsonResult.FAILURE;
        }

        User user = userService.getOneById(userId);

        String password = userService.getPassword(userId);
        if (!password.equals(SHA256.hash(userDto.getOldPwd()))) {
            return new JsonResult(ResponseEnum.FAILURE_PASSWORD_WRONG);
        }
        String newPwd = SHA256.hash(userDto.getNewPwd());
        user.setPassword(newPwd);
        return update(user);
    }

    /**
     * 忘记密码
     *
     * @param userDto
     * @return JsonResult
     * @author yzl
     * @date 2021/8/4
     * @since 1.0
     */
    @PutMapping("/forget")
    public JsonResult updateForgetPassword(@RequestBody UserDto userDto) {
        User user = userService.getUserByUsername(userDto);
        if (ObjectUtils.isEmpty(user)) {
            return new JsonResult(ResponseEnum.FAILURE_USER_NOT_EXIST);
        }
        Supplier supplier = supplierService.getOneById(user.getId());
        if (supplier == null){
            return new JsonResult(ResponseEnum.NOT_ALLOWED_TO_USE);
        }
        if (!userDto.getProblem().equals(supplier.getProblem()) || !userDto.getAnswer().equals(supplier.getAnswer())) {
            return new JsonResult(ResponseEnum.ISSUE_OR_ANSWER_INEXISTENCE);
        }

        String newPwd = SHA256.hash(userDto.getNewPwd());
        user.setPassword(newPwd);
        update(user);
        return JsonResult.SUCCESS;
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param name 用户名
     * @return
     */
    @GetMapping("/check/name")
    public JsonResult checkName(String name) {
        User user = userService.getUserByUsername(name);
        return user == null ? JsonResult.FAILURE : new JsonResult(user);
    }
}
