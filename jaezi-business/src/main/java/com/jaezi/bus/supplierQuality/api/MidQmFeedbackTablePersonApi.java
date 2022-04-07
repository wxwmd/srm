package com.jaezi.bus.supplierQuality.api;

import com.jaezi.bus.supplierQuality.model.MidQmFeedbackTablePerson;
import com.jaezi.bus.supplierQuality.service.MidQmFeedbackTablePersonService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/19 11:29
 * @description QM反馈单可见性API
 */
@RestController
@RequestMapping("/bus/mid/qm/feedback/table")
public class MidQmFeedbackTablePersonApi extends BaseApi {

    private final MidQmFeedbackTablePersonService midQmFeedbackTablePersonService;

    public MidQmFeedbackTablePersonApi(MidQmFeedbackTablePersonService midQmFeedbackTablePersonService) {
        this.midQmFeedbackTablePersonService = midQmFeedbackTablePersonService;
    }

    /**
     * QM反馈单人员关系列表(分页)
     *
     * @param filter 分页对象
     * @return QM反馈单人员关系列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return returnObjectResult(midQmFeedbackTablePersonService.getAll(filter));
    }

    /**
     * 根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(midQmFeedbackTablePersonService.getOneById(id));
    }

    /**
     * 添加
     *
     * @param midQmFeedbackTablePerson 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody MidQmFeedbackTablePerson midQmFeedbackTablePerson) {
        return returnIntResult(midQmFeedbackTablePersonService.add(midQmFeedbackTablePerson));
    }

    /**
     * 修改
     *
     * @param midQmFeedbackTablePerson 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody MidQmFeedbackTablePerson midQmFeedbackTablePerson) {
        return returnIntResult(midQmFeedbackTablePersonService.update(midQmFeedbackTablePerson));
    }

    /**
     * 删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(midQmFeedbackTablePersonService.delete(id));
    }

    /**
     * 根据qm反馈单id查询中间表集合
     * @since 1.0
     * @author whj
     * @date 2021/8/19
     * @param qmFeedbackTableId   反馈单id
     * @return JsonResult
     */
    @GetMapping("/qmTableId/{qmFeedbackTableId}")
    public JsonResult getListByQmFeedbackTableId(@PathVariable Integer qmFeedbackTableId){
        return returnObjectResult(midQmFeedbackTablePersonService.getListByQmFeedbackTableId(qmFeedbackTableId));
    }
}

