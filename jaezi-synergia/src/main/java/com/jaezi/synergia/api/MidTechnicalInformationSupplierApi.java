package com.jaezi.synergia.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.synergia.model.MidTechnicalInformationSupplier;
import com.jaezi.synergia.service.MidTechnicalInformationSupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/9/1 16:49
 * @description 技术资料交流可见性API
 */
@RestController
@RequestMapping("/syn/mid/technicalInformation/person")
public class MidTechnicalInformationSupplierApi extends BaseApi {
    private final MidTechnicalInformationSupplierService midTechnicalInformationSupplierService;

    public MidTechnicalInformationSupplierApi(MidTechnicalInformationSupplierService midTechnicalInformationSupplierService) {
        this.midTechnicalInformationSupplierService = midTechnicalInformationSupplierService;
    }

    /**
     * 技术资料交流和人员关系列表(分页)
     *
     * @param filter 分页对象
     * @return 技术图档和人员关系列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return returnObjectResult(midTechnicalInformationSupplierService.getAll(filter));
    }

    /**
     * 根据id获取技术资料交流和人员关系列表
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(midTechnicalInformationSupplierService.getOneById(id));
    }

    /**
     * 技术资料交流和人员关系列表添加
     *
     * @param midTechnicalInformationSupplier 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody MidTechnicalInformationSupplier midTechnicalInformationSupplier) {
        return returnIntResult(midTechnicalInformationSupplierService.add(midTechnicalInformationSupplier));
    }

    /**
     * 技术资料交流和人员关系列表修改
     *
     * @param midTechnicalInformationSupplier 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody MidTechnicalInformationSupplier midTechnicalInformationSupplier) {
        return returnIntResult(midTechnicalInformationSupplierService.update(midTechnicalInformationSupplier));
    }

    /**
     * 技术资料交流和人员关系列表删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(midTechnicalInformationSupplierService.delete(id));
    }

    /**
     * 根据技术资料交流id查询中间表集合
     * @since 1.0
     * @author whj
     * @date 2021/8/19
     * @param technicalInformationId   技术资料交流id
     * @return JsonResult
     */
    @GetMapping("/technicalInformationId/{technicalInformationId}")
    public JsonResult getListByTechnicalInformationId(@PathVariable Integer technicalInformationId){
        return returnObjectResult(midTechnicalInformationSupplierService.getListByTechnicalInformationId(technicalInformationId));
    }
}
