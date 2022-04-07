package com.jaezi.bus.purchase.api;

import com.jaezi.bus.purchase.model.LogisticsInformationTracking;
import com.jaezi.bus.purchase.service.LogisticsInformationTrackingService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 物流信息跟踪请求层
 */
@RestController
@RequestMapping("/bus/logistics")
public class LogisticsInformationTrackingApi extends BaseApi {

    private final LogisticsInformationTrackingService logisticsInformationTrackingService;

    public LogisticsInformationTrackingApi(LogisticsInformationTrackingService logisticsInformationTrackingService) {
        this.logisticsInformationTrackingService = logisticsInformationTrackingService;
    }

    /**
     * 采购调整列表(分页)
     *
     * @param filter 分页对象
     * @return 装车单打印列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(logisticsInformationTrackingService.getAll(filter));
    }

    /**
     * 根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(logisticsInformationTrackingService.getOneById(id));
    }

    /**
     * 修改
     *
     * @param logisticsInformationTracking 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody LogisticsInformationTracking logisticsInformationTracking) {
        return returnIntResult(logisticsInformationTrackingService.update(logisticsInformationTracking));
    }

}
