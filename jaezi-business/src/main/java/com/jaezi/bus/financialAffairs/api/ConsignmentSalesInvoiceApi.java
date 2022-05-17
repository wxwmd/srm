package com.jaezi.bus.financialAffairs.api;

import com.jaezi.bus.financialAffairs.dto.ConsignmentSalesInvoiceDto;
import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoice;
import com.jaezi.bus.financialAffairs.service.ConsignmentSalesInvoiceOutService;
import com.jaezi.bus.financialAffairs.service.ConsignmentSalesInvoiceService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author wxx
 * @version v2.0
 * @corporation copyright by jaezi.com
 * @date 2022/5/11
 * @description 寄售物资发票API
 */
@RestController
@RequestMapping("/bus/consignment/sales/invoice")
public class ConsignmentSalesInvoiceApi extends BaseApi {

    private final ConsignmentSalesInvoiceService consignmentSalesInvoice;

    public ConsignmentSalesInvoiceApi(ConsignmentSalesInvoiceService consignmentSalesInvoice) {
        this.consignmentSalesInvoice = consignmentSalesInvoice;
    }

    /**
     * 寄售物资发票列表(分页)
     *
     * @param filter 查询条件
     * @return 寄售物资发票拆分信息
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        if (userType != null) {
            if (userType == 1 || userType == 3) {
                filter.put("realName", JwtUtil.getRealName(request));
                return returnObjectResult(consignmentSalesInvoice.findAll(filter));
            }
            return new JsonResult(ResponseEnum.Do_NOT_LOOK_AT);
        }
        return new JsonResult(ResponseEnum.Do_NOT_LOOK_AT);
    }

    /**
     * 寄售物资发票根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable Integer id) {
        return returnObjectResult(consignmentSalesInvoice.getOneById(id));
    }

    /**
     * 寄售物资发票修改
     *
     * @param consignmentSalesInvoice 对象
     * @return 修改是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody ConsignmentSalesInvoice consignmentSalesInvoice) {
        int update = this.consignmentSalesInvoice.update(consignmentSalesInvoice);
        if (update == 1) {
            return JsonResult.SUCCESS;
        } else if (update == 2) {
            return new JsonResult(ResponseEnum.HAVE_MINUS);
        } else if (update==3){
            return new JsonResult(ResponseEnum.KEEP_IN_UNMODIFIED);
        }
        return new JsonResult(ResponseEnum.UPDATE);
    }

    /**
     * 寄售物资发票添加
     *
     * @param consignmentSalesInvoice 对象
     * @return 添加是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody ConsignmentSalesInvoice consignmentSalesInvoice) {
        return returnIntResult(this.consignmentSalesInvoice.add(consignmentSalesInvoice));
    }

    /**
     * 废弃寄售物资发票
     *
     * @param id id
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        int result = consignmentSalesInvoice.delete(id);
        if (result > 0) {
            return JsonResult.SUCCESS;
        } else if (result==-1) {
            return new JsonResult(ResponseEnum.KEEP_IN_UNDELETABLE);
        }else{
            return new JsonResult(ResponseEnum.INVOICE_DATA_HAVE_PROBLEM);
        }

    }

    /**
     * 寄售物资订单拆分
     *
     * @param consignmentSalesInvoices 拆分订单数据集合
     * @param request                  request对象
     * @return JsonResult
     * @author yx
     * @date 2021年8月27日15:29:52
     * @since 1.0
     */
    @PutMapping("/split")
    public JsonResult addSplitInvoice(@RequestBody List<ConsignmentSalesInvoice> consignmentSalesInvoices, HttpServletRequest request) {
        String token = request.getHeader("Credential");
        Integer quota = JwtUtil.getQuota(token);
        if (quota == null || quota == 0) {
            return new JsonResult(ResponseEnum.NO_QUOTA);
        }
        if (consignmentSalesInvoices.size() != 0) {
            List<ConsignmentSalesInvoice> consignmentSalesInvoicesList = consignmentSalesInvoice.addSplitInvoice(consignmentSalesInvoices, quota);
            if (consignmentSalesInvoicesList.size() == consignmentSalesInvoices.size()) {
                return JsonResult.SUCCESS;
            }
            return new JsonResult(consignmentSalesInvoicesList);
        }
        return new JsonResult(ResponseEnum.ORDER_UNSELECTED);
    }

    /**
     * 寄售物资发票订单合并
     *
     * @param consignmentSalesInvoiceDto 未开票对象集合
     * @return 对象详情
     */
    @PostMapping("/merge")
    public JsonResult addMerge(@RequestBody ConsignmentSalesInvoiceDto consignmentSalesInvoiceDto, HttpServletRequest request) {
        System.out.println("------------------------------");
        System.out.println(consignmentSalesInvoiceDto);


        String token = request.getHeader("Credential");
        //没看到有这个限额的要求，直接给个Integer.MAXVALUE拉到
        Integer quota=Integer.MAX_VALUE;
        String username = JwtUtil.getUsername(token);
        if (consignmentSalesInvoiceDto != null) {
            int result = consignmentSalesInvoice.addMerge(consignmentSalesInvoiceDto, quota, username);
            if (result != 0) {
                return JsonResult.SUCCESS;
            }
            return new JsonResult(ResponseEnum.AGGREGATE_AMOUNT_ERROR);
        }
        return new JsonResult(ResponseEnum.ORDER_UNSELECTED);
    }

}
