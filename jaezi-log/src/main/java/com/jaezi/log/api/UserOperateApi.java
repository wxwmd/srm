package com.jaezi.log.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.log.service.UserOperateService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2020/4/16 17:12
 * @description 用户操作日志API
 */
@RestController
@RequestMapping("/sys/user/operate")
public class UserOperateApi extends BaseApi {
    private final UserOperateService userOperateService;

    public UserOperateApi(UserOperateService userOperateService) {
        this.userOperateService = userOperateService;
    }

    /**
     * 用户操作日志列表（分页）
     *
     * @param filter 分页对象
     * @return 字典类型列表
     */
    @GetMapping
    public JsonResult getDictTypeAll(@RequestParam Map<String, String> filter) {
        return new JsonResult(userOperateService.getAll(filter));
    }

    /**
     * 根据id获取用户操作日志
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getDictTypeOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(userOperateService.getOneById(id));
    }
}
