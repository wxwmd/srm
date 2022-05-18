package com.jaezi.bus.finance.api;

import com.jaezi.bus.finance.model.ActualPayment;
import com.jaezi.bus.finance.service.ActualPaymentService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/6 9:43
 * @description 实际付款情况的API
 */
@RestController
@RequestMapping("/bus/actual/payment")
public class ActualPaymentApi extends BaseApi {

    private final ActualPaymentService actualPaymentService;

    public ActualPaymentApi(ActualPaymentService actualPaymentService) {
        this.actualPaymentService = actualPaymentService;
    }

    /**
     * 实际付款情况列表(分页)
     *
     * @param filter 分页对象
     * @return 实际付款情况列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        if (userType!=null && userType==1){
            filter.put("realName", JwtUtil.getRealName(request));
        }
        return returnObjectResult(actualPaymentService.getAll(filter));
    }

    /**
     * 实际付款情况根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(actualPaymentService.getOneById(id));
    }

    /**
     * 实际付款情况添加
     *
     * @param actualPayment 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody ActualPayment actualPayment) {
        return returnIntResult(actualPaymentService.add(actualPayment));
    }

    /**
     * 实际付款情况修改
     *
     * @param actualPayment 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody ActualPayment actualPayment) {
        return returnIntResult(actualPaymentService.update(actualPayment));
    }

    /**
     * 实际付款情况删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(actualPaymentService.delete(id));
    }
}
