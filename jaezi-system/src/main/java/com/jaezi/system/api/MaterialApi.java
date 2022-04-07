package com.jaezi.system.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.system.model.Material;
import com.jaezi.system.service.MaterialService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/5/19 19:13
 * @description 物料API
 */
@RestController
@RequestMapping("/sys/material")
public class MaterialApi extends BaseApi {

    private final MaterialService materialService;

    public MaterialApi(MaterialService materialService) {
        this.materialService = materialService;
    }

    /**
     * 物料列表（分页）
     *
     * @param filter 分页对象
     * @return 字典类型列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return new JsonResult(materialService.getAll(filter));
    }

    /**
     * 物料根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") String id) {
        return returnObjectResult(materialService.getOneById(id));
    }

    /**
     * 物料添加
     *
     * @param material 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody Material material) {
        return returnIntResult(materialService.add(material));
    }

    /**
     * 物料修改
     *
     * @param material 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody Material material) {
        if (materialService.update(material) != 0){
            return JsonResult.SUCCESS;
        }else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 物料删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable String id) {
        return returnIntResult(materialService.delete(id));
    }

    /**
     * 物料导入
     *
     * @param file
     * @return
     */
    @PostMapping("/import")
    public JsonResult excelImport(@RequestParam(value = "file") MultipartFile file) {
        materialService.materielImport(file);
        return JsonResult.SUCCESS;
    }

    /**
     * 物料模板导出
     *
     * @param response
     * @return
     */
    @GetMapping("/export")
    public void templateExport(HttpServletResponse response) {
        materialService.templateExport(response);
    }
}
