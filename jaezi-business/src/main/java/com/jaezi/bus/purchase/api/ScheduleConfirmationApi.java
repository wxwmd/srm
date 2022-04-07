package com.jaezi.bus.purchase.api;

import com.jaezi.bus.purchase.model.ScheduleConfirmation;
import com.jaezi.bus.purchase.service.ScheduleConfirmationService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 再计划时间确认API
 */
@RestController
@RequestMapping("/bus/schedule")
public class ScheduleConfirmationApi extends BaseApi {

    private final ScheduleConfirmationService scheduleConfirmationService;

    public ScheduleConfirmationApi(ScheduleConfirmationService scheduleConfirmationService) {
        this.scheduleConfirmationService = scheduleConfirmationService;
    }

    /**
     * 再计划时间确认列表(分页)
     *
     * @param filter 分页对象
     * @return 再计划时间确认列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(scheduleConfirmationService.getAll(filter));
    }

    /**
     * 再计划时间确认根据id获取
     *
     * @param id ID
     * @return 再计划时间确认详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(scheduleConfirmationService.getOneById(id));
    }

    /**
     * 再计划时间确认根据条件导出excel到浏览器
     *
     * @param filter 对象
     * @return 操作是否成功
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response, HttpServletRequest request,
                       @RequestParam Map<String, String> filter) throws Exception {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        scheduleConfirmationService.export(response, filter);
    }

    /**
     * 再计划时间确认修改
     *
     * @param scheduleConfirmation 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody ScheduleConfirmation scheduleConfirmation) {
        return returnIntResult(scheduleConfirmationService.update(scheduleConfirmation));
    }
}
