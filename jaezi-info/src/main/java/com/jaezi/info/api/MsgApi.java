package com.jaezi.info.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.info.dto.MsgDto;
import com.jaezi.info.model.Msg;
import com.jaezi.info.service.MsgService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/22  16:46
 * @description 新闻API
 */
@RestController
@RequestMapping("/info/message")
public class MsgApi extends BaseApi {

    private final MsgService msgService;

    public MsgApi(MsgService msgService) {
        this.msgService = msgService;
    }

    /**
     * 新闻列表(分页)
     *
     * @param filter 分页对象
     * @return 公告列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return returnObjectResult(msgService.getAll(filter));
    }

    /**
     * 新闻根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(msgService.getOneById(id));
    }

    /**
     * 新闻添加
     *
     * @param msgDto 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody MsgDto msgDto) {
        return returnIntResult((msgService.add(msgDto)));
    }

    /**
     * 新闻修改
     *
     * @param msg 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody Msg msg) {
        return returnIntResult(msgService.update(msg));
    }

    /**
     * 新闻删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(msgService.delete(id));
    }

    /**
     * 新闻置顶
     *
     * @param msg 对象
     * @return 操作是否成功
     */
    @PutMapping("/top")
    public JsonResult updateTop(@RequestBody Msg msg) {
        return returnIntResult(msgService.updateTop(msg.getId(), msg.getSort()));
    }

    /**
     * 查询首页新闻
     *
     * @param
     * @return JsonResult 企业新闻 enterpriseNews 5条 企业公告 enterpriseAnnouncement 5条 行业动态 industryDynamics 10条
     * @author yx
     * @date 2021年9月26日15:27:24
     * @since 1.0
     */
    @GetMapping("/newest")
    public JsonResult getNewest() {
        return returnObjectResult(msgService.getNewest());
    }

}
