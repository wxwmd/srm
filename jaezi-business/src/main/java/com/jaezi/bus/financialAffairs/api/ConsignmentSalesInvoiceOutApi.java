package com.jaezi.bus.financialAffairs.api;

import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoiceOut;
import com.jaezi.bus.financialAffairs.service.ConsignmentSalesInvoiceOutService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wxw
 * @version v2.0
 * @corporation copyright by jaezi.com
 * @date 2022/04/26
 * @description 寄售物资开票API
 */
@RestController
@RequestMapping("/bus/consignment/sales/invoice/out")
public class ConsignmentSalesInvoiceOutApi extends BaseApi {

    private final ConsignmentSalesInvoiceOutService consignmentSalesInvoiceOutService;

    public ConsignmentSalesInvoiceOutApi(ConsignmentSalesInvoiceOutService consignmentSalesInvoiceOutService) {
        this.consignmentSalesInvoiceOutService = consignmentSalesInvoiceOutService;
    }

    /**
     * 寄售物资开票列表(分页)
     *
     * @param filter 查询条件
     * @return 寄售物资发票信息
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) throws ParseException {
        if (JwtUtil.getRealName(request)!=null && !Objects.equals(JwtUtil.getRealName(request), "")){
            Integer userType = JwtUtil.getUserType(request);
            filter.put("userType", String.valueOf(userType));
            filter.put("realName", JwtUtil.getRealName(request));
            return returnObjectResult(consignmentSalesInvoiceOutService.findAll(filter));
        }else {
            return new JsonResult(ResponseEnum.FAILURE_UNAUTHORIZED);
        }
    }

    /**
     * 获取所有本供应商的开票区间
     *
     * @param request request对象
     * @return JsonResult 不重复区间
     * @author yx
     * @date 2021年8月31日14:36:11
     * @since 1.0
     */
    @GetMapping("/interval")
    public JsonResult getInterval(HttpServletRequest request) {
        Map<String, String> filter = new HashMap<>(2);
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(consignmentSalesInvoiceOutService.getInterval(filter));
    }

    /**
     * 寄售物资开票根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable Integer id) {
        return returnObjectResult(consignmentSalesInvoiceOutService.getOneById(id));
    }

    /**
     * 寄售物资开票修改
     *
     * @param consignmentSalesInvoiceOut 对象
     * @return 修改是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody ConsignmentSalesInvoiceOut consignmentSalesInvoiceOut) {
        return returnIntResult(consignmentSalesInvoiceOutService.update(consignmentSalesInvoiceOut));
    }

    /**
     * 寄售物资开票添加
     *
     * @param consignmentSalesInvoiceOut 对象
     * @return 添加是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody ConsignmentSalesInvoiceOut consignmentSalesInvoiceOut) {
        consignmentSalesInvoiceOut.setCreateTime(String.valueOf(System.currentTimeMillis()));
        return returnIntResult(consignmentSalesInvoiceOutService.add(consignmentSalesInvoiceOut));
    }

    /**
     * 寄售物资开票删除
     *
     * @param id id
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(consignmentSalesInvoiceOutService.delete(id));
    }

    /**
     * 寄售物资开票校验
     *
     * @param outInvoices 验证订单集合
     * @param request     request对象
     * @return JsonResult
     * @author yx
     * @date 2021年8月27日15:31:59
     * @since 1.0
     */
    @PostMapping("/verification")
    public JsonResult addVerification(@RequestBody List<ConsignmentSalesInvoiceOut> outInvoices, HttpServletRequest request) {
        Integer quota = JwtUtil.getQuota(request);
        if (quota == null || quota == 0) {
            return new JsonResult(ResponseEnum.NO_QUOTA);
        }
        if (outInvoices.size() != 0) {
            int verification = consignmentSalesInvoiceOutService.addVerification(outInvoices, quota);
            if (verification == outInvoices.size()) {
                return JsonResult.SUCCESS;
            }
            return new JsonResult(ResponseEnum.ORDER_AGGREGATE_AMOUNT_ERROR);
        }
        return new JsonResult(ResponseEnum.ORDER_UNSELECTED);
    }

    /**
     * 寄售物资开票信息导出
     *
     * @param filter   搜索条件
     * @param response response对象
     * @param request  request对象
     * @return void
     * @author yx
     * @date 2021年8月30日16:33:33
     * @since 1.0
     */
    @GetMapping("/export")
    public void exportControl(@RequestParam Map<String, String> filter, HttpServletResponse response, HttpServletRequest request) throws Exception {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        consignmentSalesInvoiceOutService.export(response, filter);
    }

    /**
     * 根据寄售物资id集合查询不含税金额，税额，税价合计
     *
     * @param ids 寄售物资开票id
     * @return JsonResult 不含税金额，税额，税价合计
     * @author yx
     * @date 2021年9月2日16:42:51
     * @since 1.0
     */
    @PostMapping("/money")
    public JsonResult getMoney(@RequestBody Map<String, List<String>> ids) {
        List<String> ids1 = ids.get("ids");
        if (0 != ids1.size()) {
            return returnObjectResult(consignmentSalesInvoiceOutService.getMoney(ids1));
        }
        return JsonResult.FAILURE;
    }

}
