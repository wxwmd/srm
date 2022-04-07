package com.jaezi.bus.plan.api;

import com.jaezi.bus.plan.service.SupplyDiffService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 供需差异API
 */
@RestController
@RequestMapping("/bus/supply")
public class SupplyDiffApi extends BaseApi {

    private final SupplyDiffService supplyDiffService;

    public SupplyDiffApi(SupplyDiffService supplyDiffService) {
        this.supplyDiffService = supplyDiffService;
    }

    /**
     * 供需差异列表(分页)
     *
     * @param filter 分页对象
     * @return 供需差异列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        String username = filter.get("username");
        if(ObjectUtils.isEmpty(username)) {
            Integer userType = JwtUtil.getUserType(request);
            filter.put("userType", String.valueOf(userType));
            filter.put("realName", JwtUtil.getRealName(request));
        }else {
            Map<String, Object> userMap = supplyDiffService.getUser(username);
            Map user = (Map) userMap.get(username);

            filter.put("userType", user.get("user_type").toString());
            filter.put("realName", user.get("real_name").toString());
        }
        filter.put("date", filter.get("date"));
        return returnObjectResult(supplyDiffService.getAll(filter));
    }

    /**
     * 供需差异根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(supplyDiffService.getOneById(id));
    }

    /**
     * 供需差异获取时间
     */
    @GetMapping("/time")
    public JsonResult getTime() {
        return returnObjectResult(supplyDiffService.getTime());
    }
}
