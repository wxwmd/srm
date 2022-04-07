package com.jaezi.bus.financialAffairs.api;

import com.jaezi.bus.financialAffairs.model.StandardInvoiceOutInfo;
import com.jaezi.bus.financialAffairs.service.StandardInvoiceOutInfoService;
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
 * @date 2021/8/27  16:14:28
 * @description 标准物资开票信息API
 */
@RestController
@RequestMapping("bus/standard/invoice/out/info")
public class StandardInvoiceOutInfoApi extends BaseApi {

    private final StandardInvoiceOutInfoService standardInvoiceOutInfoService;

    public StandardInvoiceOutInfoApi(StandardInvoiceOutInfoService standardInvoiceOutInfoService) {
        this.standardInvoiceOutInfoService = standardInvoiceOutInfoService;
    }

    /**
     * 标准物资开票信息列表(分页)
     *
     * @param filter 查询条件
     * @return 寄售物资发票信息
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(standardInvoiceOutInfoService.findAll(filter));
    }

    /**
     * 标准物资开票信息根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable Integer id) {
        return returnObjectResult(standardInvoiceOutInfoService.getOneById(id));
    }

    /**
     * 标准物资开票信息修改
     *
     * @param standardInvoiceOutInfo 对象
     * @return 修改是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody StandardInvoiceOutInfo standardInvoiceOutInfo) {
        return returnIntResult(standardInvoiceOutInfoService.update(standardInvoiceOutInfo));
    }

    /**
     * 标准物资开票信息添加
     *
     * @param standardInvoiceOutInfo 对象
     * @return 添加是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody StandardInvoiceOutInfo standardInvoiceOutInfo) {
        return returnIntResult(standardInvoiceOutInfoService.add(standardInvoiceOutInfo));
    }

    /**
     * 标准物资开票信息删除
     *
     * @param id id
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(standardInvoiceOutInfoService.delete(id));
    }
}
