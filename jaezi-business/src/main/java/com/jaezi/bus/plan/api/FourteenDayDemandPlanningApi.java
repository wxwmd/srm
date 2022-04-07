package com.jaezi.bus.plan.api;

import com.jaezi.bus.plan.service.FourteenDayDemandPlanningService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 双周供需核查API
 */
@RestController
@RequestMapping("/bus/fourteen")
public class FourteenDayDemandPlanningApi extends BaseApi {
    private final FourteenDayDemandPlanningService fourteenDayDemandPlanningService;

    public FourteenDayDemandPlanningApi(FourteenDayDemandPlanningService fourteenDayDemandPlanningService) {
        this.fourteenDayDemandPlanningService = fourteenDayDemandPlanningService;
    }

    /**
     * 双周供需核查列表(分页)
     *
     * @param filter 分页对象
     * @return 十四天需求计划列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(fourteenDayDemandPlanningService.getAll(filter));
    }

    /**
     * 双周供需核查根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(fourteenDayDemandPlanningService.getOneById(id));
    }
}
