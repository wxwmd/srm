package com.jaezi.bus.purchase.api;

import com.jaezi.bus.plan.service.SupplyDiffService;
import com.jaezi.bus.purchase.service.LoadingDocumentService;
import com.jaezi.bus.purchase.service.PurchaseService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 供需总览API
 */
@RestController
@RequestMapping("/bus/synopsis")
public class SupplyDemandSynopsisApi extends BaseApi {

    private final PurchaseService purchaseService;
    private final LoadingDocumentService loadingDocumentService;
    private final SupplyDiffService supplyDiffService;

    public SupplyDemandSynopsisApi(PurchaseService purchaseService, LoadingDocumentService loadingDocumentService, SupplyDiffService supplyDiffService) {
        this.purchaseService = purchaseService;
        this.loadingDocumentService = loadingDocumentService;
        this.supplyDiffService = supplyDiffService;
    }

    /**
     * 供需总览列表
     *
     * @param role     角色
     * @param username 用户名
     * @return 供需总览列表
     */
    @GetMapping
    public JsonResult Synopsis(@RequestParam(value = "role", required = false) Integer role,
                               @RequestParam(value = "username", required = false) String username,
                               HttpServletRequest request) {
        Map map = new HashMap<>(2);

        String userType = String.valueOf(JwtUtil.getUserType(request));
        String realName = JwtUtil.getRealName(request);
        if (!ObjectUtils.isEmpty(username)) {
            Map<String, Object> userMap = supplyDiffService.getUser(username);
            Map user = (Map) userMap.get(username);
            userType = user.get("user_type").toString();
            realName = user.get("real_name").toString();
        }

        //交付异常汇总
        map.put("abnormalDelivery", supplyDiffService.selectAbnormalDeliverySum(userType, realName, role));

        //供需差异汇总
        map.put("diff", supplyDiffService.selectDiffSum(userType, realName, role));

        return returnObjectResult(map);
    }

}
