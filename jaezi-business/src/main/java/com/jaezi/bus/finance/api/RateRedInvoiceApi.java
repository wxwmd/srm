package com.jaezi.bus.finance.api;

import com.jaezi.bus.finance.model.RateRedInvoice;
import com.jaezi.bus.finance.service.RateRedInvoiceService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/25 19:02
 * @description 返利红字发票API
 */
@RestController
@RequestMapping("/bus/rateRed/invoice")
public class RateRedInvoiceApi extends BaseApi {

    private final RateRedInvoiceService rateRedInvoiceService;

    public RateRedInvoiceApi(RateRedInvoiceService rateRedInvoiceService) {
        this.rateRedInvoiceService = rateRedInvoiceService;
    }

    /**
     * 返利红字发票列表(分页)
     *
     * @param filter 分页对象
     * @return 返利红字发票列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(rateRedInvoiceService.getAll(filter));
    }

    /**
     * 返利红字发票根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(rateRedInvoiceService.getOneById(id));
    }

    /**
     * 返利红字发票添加
     *
     * @param rateRedInvoice 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody RateRedInvoice rateRedInvoice, HttpServletRequest request) {
        String username = JwtUtil.getUsername(request);
        int result = rateRedInvoiceService.addRateRedInvoice(rateRedInvoice, username);
        if (result > 0) {
            return JsonResult.SUCCESS;
        }
        return new JsonResult(ResponseEnum.NEED_DISCOUNT_REASON);
    }

    /**
     * 返利红字发票修改
     *
     * @param rateRedInvoice 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody RateRedInvoice rateRedInvoice) {
        return returnIntResult(rateRedInvoiceService.update(rateRedInvoice));
    }

    /**
     * 返利红字发票删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(rateRedInvoiceService.delete(id));
    }

}
