package com.jaezi.bus.direct.api;

import com.jaezi.bus.direct.model.LoadingDirectDelivery;
import com.jaezi.bus.direct.service.LoadingDirectDeliveryService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/17 17:09
 * @description 直达装车单API
 */
@RestController
@RequestMapping("/bus/loading/direct/delivery")
public class LoadingDirectDeliveryApi extends BaseApi {

    private final LoadingDirectDeliveryService loadingDirectDeliveryService;

    public LoadingDirectDeliveryApi(LoadingDirectDeliveryService loadingDirectDeliveryService) {
        this.loadingDirectDeliveryService = loadingDirectDeliveryService;
    }

    /**
     * 直达装车单列表(分页)
     *
     * @param filter 分页对象
     * @return 直达装车单列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        String username = JwtUtil.getUsername(request);
        filter.put("supplierCode", username);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(loadingDirectDeliveryService.findAll(filter));
    }

    /**
     * 根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(loadingDirectDeliveryService.getOneById(id));
    }

    /**
     * 添加
     *
     * @param loadingDirectDelivery 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody LoadingDirectDelivery loadingDirectDelivery, HttpServletRequest request) {
        String username = JwtUtil.getUsername(request);
        loadingDirectDelivery.setSupplierCode(username);
        loadingDirectDelivery.setCreateDate(String.valueOf(LocalDate.now()));
        return returnIntResult(loadingDirectDeliveryService.add(loadingDirectDelivery));
    }

    /**
     * 修改
     *
     * @param loadingDirectDelivery 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody LoadingDirectDelivery loadingDirectDelivery) {
        return returnIntResult(loadingDirectDeliveryService.update(loadingDirectDelivery));
    }

    /**
     * 删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(loadingDirectDeliveryService.delete(id));
    }

    /**
     * 根据条件导出excel到浏览器
     *
     * @param
     * @return 操作是否成功
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response, HttpServletRequest request,
                       @RequestParam Map<String, String> filter) throws Exception {
        Integer userType = JwtUtil.getUserType(request);
        String username = JwtUtil.getUsername(request);
        filter.put("supplierCode", username);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));

        loadingDirectDeliveryService.export(response, filter);
    }
}
