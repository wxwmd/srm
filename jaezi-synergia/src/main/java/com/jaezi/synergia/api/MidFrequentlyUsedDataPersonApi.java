package com.jaezi.synergia.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.synergia.model.MidFrequentlyUsedDataPerson;
import com.jaezi.synergia.service.MidFrequentlyUsedDataPersonService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/9/1 16:09
 * @description 常用资料可见性API
 */
@RestController
@RequestMapping("/syn/mid/frequently/person")
public class MidFrequentlyUsedDataPersonApi extends BaseApi {

    private final MidFrequentlyUsedDataPersonService midFrequentlyUsedDataPersonService;

    public MidFrequentlyUsedDataPersonApi(MidFrequentlyUsedDataPersonService midFrequentlyUsedDataPersonService) {
        this.midFrequentlyUsedDataPersonService = midFrequentlyUsedDataPersonService;
    }

    /**
     * 常用资料和人员关系列表(分页)
     *
     * @param filter 分页对象
     * @return 常用资料和人员关系列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return returnObjectResult(midFrequentlyUsedDataPersonService.getAll(filter));
    }

    /**
     * 根据id获取常用资料和人员关系列表
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(midFrequentlyUsedDataPersonService.getOneById(id));
    }

    /**
     * 常用资料和人员关系列表添加
     *
     * @param midFrequentlyUsedDataPerson 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody MidFrequentlyUsedDataPerson midFrequentlyUsedDataPerson) {
        return returnIntResult(midFrequentlyUsedDataPersonService.add(midFrequentlyUsedDataPerson));
    }

    /**
     * 常用资料和人员关系列表修改
     *
     * @param midFrequentlyUsedDataPerson 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody MidFrequentlyUsedDataPerson midFrequentlyUsedDataPerson) {
        return returnIntResult(midFrequentlyUsedDataPersonService.update(midFrequentlyUsedDataPerson));
    }

    /**
     * 常用资料和人员关系列表删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(midFrequentlyUsedDataPersonService.delete(id));
    }

    /**
     * 根据常用资料id查询中间表集合
     *
     * @param frequentlyUsedDataId 常用资料id
     * @return JsonResult
     * @author whj
     * @date 2021/8/19
     * @since 1.0
     */
    @GetMapping("/frequentlyUsedDataId/{frequentlyUsedDataId}")
    public JsonResult getListByFrequentlyUsedDataId(@PathVariable Integer frequentlyUsedDataId) {
        return returnObjectResult(midFrequentlyUsedDataPersonService.getListByFrequentlyUsedDataId(frequentlyUsedDataId));
    }
}
