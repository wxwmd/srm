package com.jaezi.synergia.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.synergia.model.MidTechnicalDataPerson;
import com.jaezi.synergia.service.MidTechnicalDataPersonService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/9/1 16:17
 * @description 技术图档可见性API
 */
@RestController
@RequestMapping("/syn/mid/technical/person")
public class MidTechnicalDataPersonApi extends BaseApi {

    private final MidTechnicalDataPersonService midTechnicalDataPersonService;

    public MidTechnicalDataPersonApi(MidTechnicalDataPersonService midTechnicalDataPersonService) {
        this.midTechnicalDataPersonService = midTechnicalDataPersonService;
    }

    /**
     * 技术图档和人员关系列表(分页)
     *
     * @param filter 分页对象
     * @return 技术图档和人员关系列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return returnObjectResult(midTechnicalDataPersonService.getAll(filter));
    }

    /**
     * 根据id获取技术图档和人员关系列表
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(midTechnicalDataPersonService.getOneById(id));
    }

    /**
     * 技术图档和人员关系列表添加
     *
     * @param midTechnicalDataPerson 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody MidTechnicalDataPerson midTechnicalDataPerson) {
        return returnIntResult(midTechnicalDataPersonService.add(midTechnicalDataPerson));
    }

    /**
     * 技术图档和人员关系列表修改
     *
     * @param midTechnicalDataPerson 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody MidTechnicalDataPerson midTechnicalDataPerson) {
        return returnIntResult(midTechnicalDataPersonService.update(midTechnicalDataPerson));
    }

    /**
     * 技术图档和人员关系列表删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(midTechnicalDataPersonService.delete(id));
    }

    /**
     * 根据技术图档id查询中间表集合
     * @since 1.0
     * @author whj
     * @date 2021/8/19
     * @param technicalDataId   技术图档id
     * @return JsonResult
     */
    @GetMapping("/technicalDataId/{technicalDataId}")
    public JsonResult getListByTechnicalDataId(@PathVariable Integer technicalDataId){
        return returnObjectResult(midTechnicalDataPersonService.getListByTechnicalDataId(technicalDataId));
    }
}
