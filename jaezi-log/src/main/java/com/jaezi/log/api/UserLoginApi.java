package com.jaezi.log.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.log.service.UserAuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2020/4/16 17:12
 * @description 用户登录日志API
 */
@RestController
@RequestMapping("/sys/user/log")
public class UserLoginApi extends BaseApi {

    private final UserAuthService userAuthService;

    public UserLoginApi(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    /**
     * 用户登录日志列表（分页）
     *
     * @param filter 分页对象
     * @return 字典类型列表
     */
    @GetMapping
    public JsonResult getDictTypeAll(@RequestParam Map<String, String> filter) {
        return new JsonResult(userAuthService.getAll(filter));
    }

    /**
     * 用户登录日志根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getDictTypeOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(userAuthService.getOneById(id));
    }

}
