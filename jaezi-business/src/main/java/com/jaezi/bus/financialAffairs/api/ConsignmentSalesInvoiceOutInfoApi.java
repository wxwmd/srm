package com.jaezi.bus.financialAffairs.api;

import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoiceOutInfo;
import com.jaezi.bus.financialAffairs.service.ConsignmentSalesInvoiceOutInfoService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/31  9:23:04
 * @description 寄售物资发票信息API
 */
@RestController
@RequestMapping("/bus/consignment/sales/invoice/out/info")
public class ConsignmentSalesInvoiceOutInfoApi extends BaseApi{

    private final ConsignmentSalesInvoiceOutInfoService consignmentSalesInvoiceOutInfoService;

    public ConsignmentSalesInvoiceOutInfoApi(ConsignmentSalesInvoiceOutInfoService consignmentSalesInvoiceOutInfoService) {
        this.consignmentSalesInvoiceOutInfoService = consignmentSalesInvoiceOutInfoService;
    }

    /**
     * 寄售物资发票信息列表(分页)
     *
     * @param filter 查询条件
     * @return 寄售物资开票信息
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(consignmentSalesInvoiceOutInfoService.findAll(filter));
    }

    /**
     * 寄售物资发票信息根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable Integer id) {
        return returnObjectResult(consignmentSalesInvoiceOutInfoService.getOneById(id));
    }

    /**
     * 寄售物资发票信息修改
     *
     * @param consignmentSalesInvoiceOutInfo 对象
     * @return 修改是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody ConsignmentSalesInvoiceOutInfo consignmentSalesInvoiceOutInfo) {
        return returnIntResult(consignmentSalesInvoiceOutInfoService.update(consignmentSalesInvoiceOutInfo));
    }

    /**
     * 寄售物资发票信息添加
     *
     * @param consignmentSalesInvoiceOutInfo 对象
     * @return 添加是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody ConsignmentSalesInvoiceOutInfo consignmentSalesInvoiceOutInfo) {
        return returnIntResult(consignmentSalesInvoiceOutInfoService.add(consignmentSalesInvoiceOutInfo));
    }

    /**
     * 寄售物资发票信息删除
     *
     * @param id id
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(consignmentSalesInvoiceOutInfoService.delete(id));
    }
}
