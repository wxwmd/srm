package com.jaezi.bus.purchase.api;

import com.jaezi.bus.purchase.service.ProductionPlanImportService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 供应商生产计划导入API
 */
@RestController
@RequestMapping("/bus/production/plan")
public class ProductionPlanImportApi extends BaseApi {
    private final ProductionPlanImportService productionPlanImportService;

    public ProductionPlanImportApi(ProductionPlanImportService productionPlanImportService) {
        this.productionPlanImportService = productionPlanImportService;
    }

    /**
     * 供应商生产计划列表(分页)
     *
     * @param filter 分页对象
     * @return 库存导入列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(productionPlanImportService.getAll(filter));
    }

    /**
     * 供应商生产计划根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(productionPlanImportService.getOneById(id));
    }

    /**
     * 供应商生产计划导入
     *
     * @param file
     * @return
     */
    @PostMapping("import")
    public JsonResult excelImport(@RequestParam(value = "file") MultipartFile file) {
        return productionPlanImportService.excelImport(file);
    }

    /**
     * 供应商生产计划模板导出
     *
     * @param response
     */
    @GetMapping("/template/export")
    public void templateExport(HttpServletResponse response) {
        productionPlanImportService.templateExport(response);
    }
}
