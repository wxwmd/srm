package com.jaezi.bus.direct.api;

import com.jaezi.bus.direct.model.LoadingDirectDeliveryDetail;
import com.jaezi.bus.direct.service.LoadingDirectDeliveryDetailService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/17 17:09
 * @description 直达装车单明细API
 */
@RestController
@RequestMapping("/bus/loading/direct/delivery/detail")
public class LoadingDirectDeliveryDetailApi extends BaseApi {

    private final LoadingDirectDeliveryDetailService loadingDirectDeliveryDetailService;

    public LoadingDirectDeliveryDetailApi(LoadingDirectDeliveryDetailService loadingDirectDeliveryDetailService) {
        this.loadingDirectDeliveryDetailService = loadingDirectDeliveryDetailService;
    }

    /**
     * 直达装车单明细列表(分页)
     *
     * @param filter 分页对象
     * @return 直达装车单列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return returnObjectResult(loadingDirectDeliveryDetailService.getAll(filter));
    }

    /**
     * 根据id获取直达装车单明细
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(loadingDirectDeliveryDetailService.getOneById(id));
    }

    /**
     * 直达装车单明细添加
     *
     * @param loadingDirectDeliveryDetail 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody LoadingDirectDeliveryDetail loadingDirectDeliveryDetail) {
        return returnIntResult(loadingDirectDeliveryDetailService.add(loadingDirectDeliveryDetail));
    }

    /**
     * 直达装车单明细修改
     *
     * @param loadingDirectDeliveryDetail 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody LoadingDirectDeliveryDetail loadingDirectDeliveryDetail) {
        return returnIntResult(loadingDirectDeliveryDetailService.update(loadingDirectDeliveryDetail));
    }

    /**
     * 直达装车单明细删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(loadingDirectDeliveryDetailService.delete(id));
    }

}
