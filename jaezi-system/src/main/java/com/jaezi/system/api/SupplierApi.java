package com.jaezi.system.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.system.dto.SupplierDto;
import com.jaezi.system.service.SupplierService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @author yzl
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/16 17:12
 * @description 供应商API
 */
@RestController
@RequestMapping("/sys/supplier")
public class SupplierApi extends BaseApi {

    private final SupplierService supplierService;

    public SupplierApi(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    /**
     * 供应商分页
     *
     * @param filter 分页对象
     * @return 对应列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        //供应商逻辑待添加
        return new JsonResult(supplierService.getAll(filter));
    }

    /**
     * 获取所有供应商
     *
     * @return 对应列表
     */
    @GetMapping("/all")
    public JsonResult getAllSupplier(@RequestParam(value = "type", required = false) String type) {
        return new JsonResult(supplierService.getAllSupplier(type));
    }

    /**
     * 根据用户id获取供应商信息
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(supplierService.getOneById(id));
    }

    /**
     * 供应商修改
     *
     * @param supplier 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody SupplierDto supplier) {
        if (supplierService.update(supplier) != 0) {
            return JsonResult.SUCCESS;
        } else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 供应商导入
     * @param file 文件对象
     * @return
     */
    @PostMapping("/import")
    public JsonResult excelImport(@RequestParam(value = "file") MultipartFile file) {
        supplierService.excelImport(file);
        return JsonResult.SUCCESS;
    }

    /**
     * 供应商模板导出
     * @param response 响应对象
     * @return
     */
    @GetMapping("/export")
    public void templateExport(HttpServletResponse response) {
        supplierService.templateExport(response);
    }

}
