package com.jaezi.bus.purchase.api;

import com.jaezi.bus.purchase.service.InventoryImportService;
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
 * @date 2018/11/19 15:39
 * @description 供应商库存导入API
 */
@RestController
@RequestMapping("/bus/supplier/inventory")
public class InventoryImportApi extends BaseApi {
    private final InventoryImportService inventoryImportService;

    public InventoryImportApi(InventoryImportService inventoryImportService) {
        this.inventoryImportService = inventoryImportService;
    }

    /**
     * 供应商库存导入列表(分页)
     *
     * @param filter 分页对象
     * @return 库存导入列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(inventoryImportService.getAll(filter));
    }

    /**
     * 供应商库存导入根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(inventoryImportService.getOneById(id));
    }

    /**
     * 供应商库存导入
     *
     * @param file
     * @return
     */
    @PostMapping("/import")
    public JsonResult excelImport(@RequestParam(value = "file") MultipartFile file) {
        return inventoryImportService.excelImport(file);
    }

    /**
     * 供应商库存导入模板导出
     *
     * @param response
     */
    @GetMapping("/template/export")
    public void templateExport(HttpServletResponse response) {
        inventoryImportService.templateExport(response);
    }
}
