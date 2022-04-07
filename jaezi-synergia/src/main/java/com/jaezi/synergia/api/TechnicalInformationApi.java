package com.jaezi.synergia.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.synergia.dto.TechnicalInformationDto;
import com.jaezi.synergia.dto.VisibleDto;
import com.jaezi.synergia.service.TechnicalInformationService;
import com.jaezi.system.model.User;
import com.jaezi.system.service.UserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/3  9:53:21
 * @description 技术资料交流API
 */
@RestController
@RequestMapping("/syn/technical/information")
public class TechnicalInformationApi extends BaseApi {

    private final TechnicalInformationService technicalInformationService;

    private final UserService userService;

    public TechnicalInformationApi(TechnicalInformationService technicalInformationService, UserService userService) {
        this.technicalInformationService = technicalInformationService;
        this.userService = userService;
    }

    /**
     * 设置技术资料交流可见状态
     *
     * @param visibleDto 接收 技术资料id集合 可见人员id集合 可见类型 的对象
     * @return JsonResult 设置失败或者失败
     * @author yx
     * @date 2021年8月9日14:01:50
     * @since 1.0
     */
    @PostMapping("/visible")
    public JsonResult setVisible(@RequestParam VisibleDto visibleDto, HttpServletRequest request) {
        User oneById = userService.getOneById(JwtUtil.getUserId(request));
        //判断当前登录用户是否为企业用户和管理员
        if (oneById.getUserType() == 0 && oneById.getUserType() == 2) {
            List<Integer> dataIdList = visibleDto.getDataIdList();
            List<Integer> visiblePersonList = visibleDto.getVisiblePersonList();
            Integer visibleType = visibleDto.getVisibleType();
            if (dataIdList.size() == 0 || visibleType == null) {
                return JsonResult.FAILURE;
            }
            return returnIntResult(technicalInformationService.setVisible(dataIdList, visiblePersonList, visibleType));
        }
        return JsonResult.FAILURE;

    }

    /**
     * 修改技术资料交流可见状态
     *
     * @param visibleDto 接收 技术资料id集合 可见人员id集合 可见类型 的对象
     * @return JsonResult 是否修改成功
     * @author yx
     * @date 2021年8月9日14:46:43
     * @since 1.0
     */
    @PutMapping("/visible")
    public JsonResult updateVisible(@RequestBody VisibleDto visibleDto, HttpServletRequest request) {
        User oneById = userService.getOneById(JwtUtil.getUserId(request));
        //判断当前登录用户是否为企业用户和管理员
        if (oneById.getUserType() == 0 && oneById.getUserType() == 2) {
            List<Integer> dataIdList = visibleDto.getDataIdList();
            List<Integer> visiblePersonList = visibleDto.getVisiblePersonList();
            Integer visibleType = visibleDto.getVisibleType();
            if (dataIdList.size() == 0 || visibleType == null) {
                return JsonResult.FAILURE;
            }
            return returnIntResult(technicalInformationService.updateVisible(dataIdList, visiblePersonList, visibleType));
        }
        return JsonResult.FAILURE;
    }

    /**
     * 查询技术资料交流
     *
     * @param filter  查询条件列表
     * @param request request对象
     * @return JsonResult 技术资料列表
     * @author yx
     * @date 2021年8月9日15:57:51
     * @since 1.0
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer personId = JwtUtil.getUserId(request);
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userId", String.valueOf(personId));
        filter.put("userType", String.valueOf(userType));
        return returnObjectResult(technicalInformationService.findAll(filter));
    }

    /**
     * 根据id查询技术资料交流
     *
     * @param id 技术资料id
     * @return JsonResult 技术资料兑现
     * TODO 方法逻辑
     * @author yx
     * @date 2021年8月9日15:56:42
     * @since 1.0
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable Integer id) {
        return returnObjectResult(technicalInformationService.getOneById(id));
    }

    /**
     * 添加技术资料交流
     *
     * @param technicalInformationDto 技术资料对象
     * @return JsonResult 是否添加成功
     * @author yx
     * @date 2021年8月9日15:55:34
     * @since 1.0
     */
    @PostMapping
    public JsonResult add(@RequestBody TechnicalInformationDto technicalInformationDto) {
        List<Integer> visiblePersonList = technicalInformationDto.getVisiblePersonList();
        Integer visibleType = technicalInformationDto.getVisible();
        if (visiblePersonList.size() > 0 || visibleType != null) {
            return returnIntResult(technicalInformationService.addTechnicalInformation(technicalInformationDto));
        }
        return new JsonResult(ResponseEnum.REPORT_NOT_HAVE_VISIBLE);
    }

    /**
     * 修改技术资料交流
     *
     * @param technicalInformationDto 技术资料对象
     * @return JsonResult
     * @author yx
     * @date 2021年8月9日15:54:45
     * @since 1.0
     */
    @PutMapping
    public JsonResult update(@RequestBody TechnicalInformationDto technicalInformationDto) {
        return returnIntResult(technicalInformationService.updateTechnicalInformation(technicalInformationDto));
    }

    /**
     * 删除技术资料交流
     *
     * @return JsonResult 是否删除成功
     * @author 填自己的名字
     * @date 2021年8月9日15:53:50
     * @since 1.0
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(technicalInformationService.delete(id));
    }

    /**
     * 技术资料交流文件下载
     *
     * @param response response对象
     * @param id       技术资料id
     * @return JsonResult 文件是否下载成功
     * @author yx
     * @date 2021年8月3日14:54:51
     * @since 1.0
     */
    @GetMapping("/download")
    public void down(HttpServletResponse response, @RequestParam Integer id,String fileName) {
        technicalInformationService.down(response, id, fileName);
    }


    /**
     * 技术资料交流文件上传
     *
     * @param file 上传的文件
     * @return JsonResult 文件是否上传成功
     * @author yx
     * @date 2021年8月3日14:25:04
     * @since 1.0
     */
    @PostMapping("/upload")
    public JsonResult upload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return JsonResult.FAILURE;
        }
        String url = technicalInformationService.upload(file);
        if (!ObjectUtils.isEmpty(url)) {
            return new JsonResult(url);
        }
        return JsonResult.FAILURE;
    }

}
