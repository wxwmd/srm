package com.jaezi.bus.plan.api;

import com.jaezi.bus.plan.service.ThirteenWeekDemandPlanningService;
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
 * @description 未来十三周API
 */
@RestController
@RequestMapping("/bus/thirteen")
public class ThirteenWeekDemandPlanningApi extends BaseApi {

    private final ThirteenWeekDemandPlanningService thirteenWeekDemandPlanningService;

    public ThirteenWeekDemandPlanningApi(ThirteenWeekDemandPlanningService thirteenWeekDemandPlanningService) {
        this.thirteenWeekDemandPlanningService = thirteenWeekDemandPlanningService;
    }

    /**
     * 未来十三周列表(分页)
     *
     * @param filter 分页对象
     * @return 十三周列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(thirteenWeekDemandPlanningService.getAll(filter));
    }

    /**
     * 未来十三周根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(thirteenWeekDemandPlanningService.getOneById(id));
    }
}
