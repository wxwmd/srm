package com.jaezi.bus.financialAffairs.api;

import com.jaezi.bus.financialAffairs.dto.StandardInvoiceDto;
import com.jaezi.bus.financialAffairs.model.StandardInvoiceOut;
import com.jaezi.bus.financialAffairs.model.StandardInvoice;
import com.jaezi.bus.financialAffairs.service.StandardInvoiceOutService;
import com.jaezi.bus.financialAffairs.service.StandardInvoiceService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/10  8:58:30
 * @description 标准物资发票API
 */
@RestController
@RequestMapping("/bus/standard/invoice")
public class StandardInvoiceApi extends BaseApi {

    private final StandardInvoiceService standardInvoiceService;

    private final StandardInvoiceOutService standardInvoiceOutService;

    public StandardInvoiceApi(StandardInvoiceService standardInvoiceService, StandardInvoiceOutService standardInvoiceOutService) {
        this.standardInvoiceService = standardInvoiceService;
        this.standardInvoiceOutService = standardInvoiceOutService;
    }

    /**
     * 查询已拆单标准物资发票订单
     *
     * @param filter 查询条件
     * @return 已拆单标准物资订单信息
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(standardInvoiceService.findAll(filter));
    }

    /**
     * 标准物资发票根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable Integer id) {
        return returnObjectResult(standardInvoiceService.getOneById(id));
    }

    /**
     * 标准物资发票修改
     *
     * @param standardInvoice 对象
     * @return 修改是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody StandardInvoice standardInvoice) {
        if (standardInvoiceService.update(standardInvoice) != 0) {
            return JsonResult.SUCCESS;
        } else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 标准物资发票添加
     *
     * @param standardInvoice 对象
     * @return 添加是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody StandardInvoice standardInvoice) {
        standardInvoice.setInvoiceType(0);
        return returnIntResult(standardInvoiceService.add(standardInvoice));
    }

    /**
     * 废弃标准物资发票
     *
     * @param id id
     * @return 废弃是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        int remove = standardInvoiceService.delete(id);
        if (remove == 0) {
            return new JsonResult(ResponseEnum.KEEP_IN_UNDELETABLE);
        } else if (remove == -1) {
            return new JsonResult(ResponseEnum.INVOICE_DATA_HAVE_PROBLEM);
        }
        return JsonResult.SUCCESS;
    }

    /**
     * 标准物资发票订单拆分
     *
     * @param standardInvoiceDto 拆分订单数据集合
     * @param request            request对象
     * @return JsonResult
     * @author yx
     * @date 2021年8月27日15:29:52
     * @since 1.0
     */
    @PutMapping("/split")
    public JsonResult addSplitInvoice(@RequestBody StandardInvoiceDto standardInvoiceDto, HttpServletRequest request) {
        String token = request.getHeader("Credential");
        Integer quota = JwtUtil.getQuota(token);
        if (quota == null) {
            return new JsonResult(ResponseEnum.NO_QUOTA);
        }
        BigDecimal aggregateAmount = new BigDecimal(standardInvoiceDto.getAggregateAmount());
        List<StandardInvoice> standardInvoices = standardInvoiceDto.getStandardInvoices();
        int size = standardInvoices.size();
        if (size != 0) {
            int verification = standardInvoiceService.addSplitInvoice(standardInvoices, aggregateAmount, quota);
            if (verification == size) {
                StandardInvoice split = standardInvoices.get(0);
                StandardInvoiceOut byPurchaseOrder = standardInvoiceOutService.getByPurchaseOrder(split.getPurchaseOrder(), split.getMaterial());
                if (byPurchaseOrder == null) {
                    return new JsonResult(ResponseEnum.ORDER_INFO_BLANK);
                }
                //设置主发票的状态为已开票
                //未开票数量设置为0
                byPurchaseOrder.setNotOutInvoiceNumber(new BigDecimal("0.00"));
                if (standardInvoiceOutService.update(byPurchaseOrder) != 0) {
                    return JsonResult.SUCCESS;
                }
                return JsonResult.FAILURE;
            }
            return new JsonResult(ResponseEnum.ORDER_SUM_ERROR);
        }
        return JsonResult.FAILURE;
    }

    /**
     * 标准物资发票订单合并
     *
     * @param standardInvoices 合并对象集合
     * @return 是否合并成功
     */
    @PostMapping("/merge")
    public JsonResult addMerge(@RequestBody List<StandardInvoice> standardInvoices, HttpServletRequest request) {
        String token = request.getHeader("Credential");
        Integer quota = JwtUtil.getQuota(token);
        String username = JwtUtil.getUsername(token);
        if (standardInvoices.size() != 0) {
            List<StandardInvoice> standardInvoiceList = standardInvoiceService.addMerge(standardInvoices, quota, username);

            if (standardInvoiceList.size() == standardInvoices.size()) {
                return JsonResult.SUCCESS;
            } else if (standardInvoiceList.size() == 0) {
                return new JsonResult(ResponseEnum.MERGE_ORDER_AGGREGATE_AMOUNT_ERROR);
            }
            return new JsonResult(standardInvoiceList);
        }
        return new JsonResult(ResponseEnum.ORDER_UNSELECTED);
    }

    /**
     * 超级企业用户审核标准物资发票
     *
     * @param auditData id：发票id  invoiceStatus：发票状态 auditStatus：审核状态
     * @return JsonResult
     * @author yx
     * @date 2021年10月13日18:39:30
     * @since 1.0
     */
    @GetMapping("/audit")
    public JsonResult updateAudit(@RequestParam Map<String, String> auditData, HttpServletRequest request) {
        if (JwtUtil.getUserType(request) != null && JwtUtil.getUserType(request) == 3) {
            Integer id = Integer.valueOf(auditData.get("id"));
            Integer invoiceStatus = Integer.valueOf(auditData.get("invoiceStatus"));
            Integer auditStatus = Integer.valueOf(auditData.get("auditStatus"));
            if (standardInvoiceService.updateAudit(id, invoiceStatus, auditStatus) != 0) {
                return JsonResult.SUCCESS;
            }
            return JsonResult.SUCCESS;
        }
        return new JsonResult(ResponseEnum.SUPER_ENTERPRISE_USER);
    }

}
