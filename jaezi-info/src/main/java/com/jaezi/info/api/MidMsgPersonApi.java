package com.jaezi.info.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.info.model.MidMsgPerson;
import com.jaezi.info.service.MidMsgPersonService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 新闻可见性API
 */
@RestController
@RequestMapping("/info/message/mid")
public class MidMsgPersonApi extends BaseApi {

    private final MidMsgPersonService midMsgPersonService;

    public MidMsgPersonApi(MidMsgPersonService midMsgPersonService) {
        this.midMsgPersonService = midMsgPersonService;
    }

    /**
     * 常用资料和人员关系列表(分页)
     *
     * @param filter 分页对象
     * @return 常用资料和人员关系列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return returnObjectResult(midMsgPersonService.getAll(filter));
    }

    /**
     * 根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(midMsgPersonService.getOneById(id));
    }

    /**
     * 添加
     *
     * @param midMsgPerson 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody MidMsgPerson midMsgPerson) {
        return returnIntResult(midMsgPersonService.add(midMsgPerson));
    }

    /**
     * 修改
     *
     * @param midMsgPerson 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody MidMsgPerson midMsgPerson) {
        return returnIntResult(midMsgPersonService.update(midMsgPerson));
    }

    /**
     * 删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(midMsgPersonService.delete(id));
    }

    /**
     * 根据新闻id查询中间表集合
     * @since 1.0
     * @author yzl
     * @date 2021/8/19
     * @param msgId   信息id
     * @return JsonResult
     */
    @GetMapping("/person/{msgId}")
    public JsonResult getListByMsgId(@PathVariable Integer msgId){
        return returnObjectResult(midMsgPersonService.getListByMsgId(msgId));
    }
}
