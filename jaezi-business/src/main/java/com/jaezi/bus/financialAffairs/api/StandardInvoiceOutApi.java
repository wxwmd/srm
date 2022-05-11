package com.jaezi.bus.financialAffairs.api;

import com.jaezi.bus.financialAffairs.model.StandardInvoiceOut;
import com.jaezi.bus.financialAffairs.service.StandardInvoiceOutService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/9  20:38:15
 * @description 标准物资开票API
 */
@RestController
@RequestMapping("/bus/standard/invoice/out")
public class StandardInvoiceOutApi extends BaseApi {

    private static Logger log = LoggerFactory.getLogger(StandardInvoiceOutApi.class);

    private final StandardInvoiceOutService standardInvoiceOutService;

    public StandardInvoiceOutApi(StandardInvoiceOutService standardInvoiceOutService) {
        this.standardInvoiceOutService = standardInvoiceOutService;
    }

    /**
     * 标准物资开票信息列表(分页)
     *
     * @param filter 查询条件
     * @return 标准物资开票信息
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) throws ParseException {
        Integer userType = JwtUtil.getUserType(request);
        if (JwtUtil.getRealName(request)!=null && !Objects.equals(JwtUtil.getRealName(request), "")){
            filter.put("userType", String.valueOf(userType));
            filter.put("realName", JwtUtil.getRealName(request));
            // 只取那些没有被开票的行项目
            filter.put("status","-1");
            DataGrid<StandardInvoiceOut> all = standardInvoiceOutService.findAll(filter);
            return returnObjectResult(all);
        } else {
            return new JsonResult(ResponseEnum.FAILURE_UNAUTHORIZED);
        }
    }

    /**
     * 标准物资开票订单校验
     *
     * @param standardInvoiceOuts 验证订单集合
     * @param request             request对象
     * @return JsonResult
     * @author yx
     * @date 2021年8月27日15:31:59
     * @since 1.0
     */
    @PostMapping("/verification")
    public JsonResult addVerification(@RequestBody List<StandardInvoiceOut> standardInvoiceOuts, HttpServletRequest request) {
        Integer quota = JwtUtil.getQuota(request);
        if (quota == null || quota == 0) {
            return new JsonResult(ResponseEnum.NO_QUOTA);
        }
        if (standardInvoiceOuts.size() != 0) {
            int verification = standardInvoiceOutService.addVerification(standardInvoiceOuts, quota);
            if (verification == standardInvoiceOuts.size()) {
                return JsonResult.SUCCESS;
            }
            return new JsonResult(ResponseEnum.ORDER_AGGREGATE_AMOUNT_ERROR);
        }
        return new JsonResult(ResponseEnum.ORDER_UNSELECTED);
    }

    /**
     * 标准物资开票根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable Integer id) {
        return returnObjectResult(standardInvoiceOutService.getOneById(id));
    }

    /**
     * 标准物资开票修改
     *
     * @param standardInvoiceOut 对象
     * @return 修改是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody StandardInvoiceOut standardInvoiceOut) {
        return returnIntResult(standardInvoiceOutService.update(standardInvoiceOut));
    }

    /**
     * 标准物资开票添加
     *
     * @param standardInvoiceOut 对象
     * @return 添加是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody StandardInvoiceOut standardInvoiceOut) {
        standardInvoiceOut.setCreateTime(String.valueOf(System.currentTimeMillis()));
        return returnIntResult(standardInvoiceOutService.add(standardInvoiceOut));
    }

    /**
     * 标准物资开票删除
     *
     * @param id id
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(standardInvoiceOutService.delete(id));
    }

    /**
     * 标准物资开票信息导出
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
        standardInvoiceOutService.export(response, filter);
    }

    /**
     * 标准物资开票订单导入
     *
     * @param file
     * @return
     */
    @PostMapping("/import")
    public JsonResult excelImport(@RequestParam(value = "file") MultipartFile file) {
        standardInvoiceOutService.excelImport(file);
        return JsonResult.SUCCESS;
    }

    /**
     * 标准物资开票订单导出
     *
     * @param response
     * @return
     */
    @GetMapping("/template/export")
    public void templateExport(HttpServletResponse response) {
        standardInvoiceOutService.templateExport(response);
    }

}
