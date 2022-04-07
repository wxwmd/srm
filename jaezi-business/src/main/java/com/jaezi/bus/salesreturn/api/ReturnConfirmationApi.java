package com.jaezi.bus.salesreturn.api;

import com.jaezi.bus.salesreturn.service.ReturnConfirmationService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 退货确认API
 */
@RestController
@RequestMapping("/bus/return")
public class ReturnConfirmationApi extends BaseApi {

    private final ReturnConfirmationService returnConfirmationService;

    public ReturnConfirmationApi(ReturnConfirmationService returnConfirmationService) {
        this.returnConfirmationService = returnConfirmationService;
    }

    /**
     * 退货确认列表(分页)
     *
     * @param filter 分页对象
     * @return 实际付款情况列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        Integer userType = JwtUtil.getUserType();
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName());
        return returnObjectResult(returnConfirmationService.getAll(filter));
    }

    /**
     * 退货确认根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(returnConfirmationService.getOneById(id));
    }

}
