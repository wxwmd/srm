package com.jaezi.bus.inventory.api;

import com.jaezi.bus.inventory.model.ConsignmentGoodsCloseNoticeLetter;
import com.jaezi.bus.inventory.service.ConsignmentGoodsCloseNoticeLetterService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/10 18:06
 * @description 寄售物资结算通知书API
 */
@RestController
@RequestMapping("/bus/consignment/goodsclose/noticeletter")
public class ConsignmentGoodsCloseNoticeLetterApi extends BaseApi {

    private final ConsignmentGoodsCloseNoticeLetterService consignmentGoodsCloseNoticeLetterService;

    public ConsignmentGoodsCloseNoticeLetterApi(ConsignmentGoodsCloseNoticeLetterService consignmentGoodsCloseNoticeLetterService) {
        this.consignmentGoodsCloseNoticeLetterService = consignmentGoodsCloseNoticeLetterService;
    }

    /**
     * 寄售物资结算通知书列表(分页)
     *
     * @param filter 分页对象
     * @return 寄售物资结算通知单列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(consignmentGoodsCloseNoticeLetterService.findAll(filter));
    }

    /**
     * 寄售物资结算通知书根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(consignmentGoodsCloseNoticeLetterService.getOneById(id));
    }

    /**
     * 寄售物资结算通知书添加
     *
     * @param consignmentGoodsCloseNoticeLetter 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody ConsignmentGoodsCloseNoticeLetter consignmentGoodsCloseNoticeLetter) {
        return returnIntResult(consignmentGoodsCloseNoticeLetterService.add(consignmentGoodsCloseNoticeLetter));
    }

    /**
     * 寄售物资结算通知书修改
     *
     * @param consignmentGoodsCloseNoticeLetter 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody ConsignmentGoodsCloseNoticeLetter consignmentGoodsCloseNoticeLetter) {
        return returnIntResult(consignmentGoodsCloseNoticeLetterService.update(consignmentGoodsCloseNoticeLetter));
    }

    /**
     * 寄售物资结算通知书删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(consignmentGoodsCloseNoticeLetterService.delete(id));
    }
}
