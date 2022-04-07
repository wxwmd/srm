package com.jaezi.bus.inventory.api;

import com.jaezi.bus.inventory.model.ConsignmentGoodsSDTR;
import com.jaezi.bus.inventory.service.ConsignmentGoodsSDTRService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/9 16:25
 * @description 寄售物资结收发存API
 */
@RestController
@RequestMapping("/bus/consignment/goodssdtr")
public class ConsignmentGoodsSDTRApi extends BaseApi {

    private final ConsignmentGoodsSDTRService consignmentGoodsSDTRService;

    public ConsignmentGoodsSDTRApi(ConsignmentGoodsSDTRService consignmentGoodsSDTRService) {
        this.consignmentGoodsSDTRService = consignmentGoodsSDTRService;
    }

    /**
     * 寄售物资结收发存列表(分页)
     *
     * @param filter 分页对象
     * @return 寄售物资结收发存列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter,HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(consignmentGoodsSDTRService.findAll(filter));
    }

    /**
     * 寄售物资结收发存根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(consignmentGoodsSDTRService.getOneById(id));
    }

    /**
     * 寄售物资结收发存添加
     *
     * @param consignmentGoodsSDTR 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody  ConsignmentGoodsSDTR consignmentGoodsSDTR) {
        return returnIntResult(consignmentGoodsSDTRService.add(consignmentGoodsSDTR));
    }

    /**
     * 寄售物资结收发存修改
     *
     * @param consignmentGoodsSDTR 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody ConsignmentGoodsSDTR consignmentGoodsSDTR) {
        return returnIntResult(consignmentGoodsSDTRService.update(consignmentGoodsSDTR));
    }

    /**
     * 寄售物资结收发存删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(consignmentGoodsSDTRService.delete(id));
    }

    /**
     * 寄售物资结收发存根据条件导出excel到浏览器
     *
     * @param
     * @return 操作是否成功
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response,HttpServletRequest request,
                       @RequestParam Map<String, String> filter) throws Exception {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        consignmentGoodsSDTRService.export(response, filter);
    }

}
