package com.jaezi.bus.supplierQuality.api;

import com.jaezi.bus.supplierQuality.model.MidQmFeedbackTablePerson;
import com.jaezi.bus.supplierQuality.model.MidSupplierQualityInformationPerson;
import com.jaezi.bus.supplierQuality.service.MidQmFeedbackTablePersonService;
import com.jaezi.bus.supplierQuality.service.MidSupplierQualityInformationPersonService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/20 16:58
 * @description 供应商质量信息API
 */
@RestController
@RequestMapping("/bus/mid/supplier/quality/information")
public class MidSupplierQualityInformationPersonApi extends BaseApi {

    private final MidSupplierQualityInformationPersonService midSupplierQualityInformationPersonService;


    public MidSupplierQualityInformationPersonApi(MidSupplierQualityInformationPersonService midSupplierQualityInformationPersonService) {
        this.midSupplierQualityInformationPersonService = midSupplierQualityInformationPersonService;
    }

    /**
     * 供应商质量信息列表(分页)
     *
     * @param filter 分页对象
     * @return QM反馈单人员关系列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return returnObjectResult(midSupplierQualityInformationPersonService.getAll(filter));
    }

    /**
     * 根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(midSupplierQualityInformationPersonService.getOneById(id));
    }

    /**
     * 添加
     *
     * @param midSupplierQualityInformationPerson 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody MidSupplierQualityInformationPerson midSupplierQualityInformationPerson) {
        return returnIntResult(midSupplierQualityInformationPersonService.add(midSupplierQualityInformationPerson));
    }

    /**
     * 修改
     *
     * @param midSupplierQualityInformationPerson 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody MidSupplierQualityInformationPerson midSupplierQualityInformationPerson) {
        return returnIntResult(midSupplierQualityInformationPersonService.update(midSupplierQualityInformationPerson));
    }

    /**
     * 删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(midSupplierQualityInformationPersonService.delete(id));
    }

    /**
     * 根据供应商质量信息id查询中间表集合
     * @since 1.0
     * @author whj
     * @date 2021/8/19
     * @param supplierQualityInformationId   供应商质量信息id
     * @return JsonResult
     */
    @GetMapping("/supplierQualityInformationId/{supplierQualityInformationId}")
    public JsonResult getListByQmFeedbackTableId(@PathVariable Integer supplierQualityInformationId){
        return returnObjectResult(midSupplierQualityInformationPersonService.getListBySupplierQualityInformationId(supplierQualityInformationId));
    }
}
