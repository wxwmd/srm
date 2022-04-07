package com.jaezi.bus.purchase.api;

import com.jaezi.bus.plan.service.SupplyDiffService;
import com.jaezi.bus.purchase.service.InventoryService;
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
 * @date 2018/11/19 15:39
 * @description 供应商库存API
 */
@RestController
@RequestMapping("/bus/inventory")
public class InventoryApi extends BaseApi {

    private final InventoryService inventoryService;
    private final SupplyDiffService supplyDiffService;

    public InventoryApi(InventoryService inventoryService, SupplyDiffService supplyDiffService) {
        this.inventoryService = inventoryService;
        this.supplyDiffService = supplyDiffService;
    }

    /**
     * 供应商库存列表(分页)
     *
     * @param filter 分页对象
     * @return 公告列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        String username = filter.get("username");
        if (!ObjectUtils.isEmpty(username)) {
            Map<String, Object> userMap = supplyDiffService.getUser(username);
            Map user = (Map) userMap.get(username);

            filter.put("userType", user.get("user_type").toString());
            filter.put("realName", user.get("real_name").toString());
        }

        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));

        return returnObjectResult(inventoryService.getAll(filter));
    }

    /**
     * 供应商库存根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(inventoryService.getOneById(id));
    }
}
