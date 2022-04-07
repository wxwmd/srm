package com.jaezi.bus.purchase.api;

import com.jaezi.bus.plan.service.SupplyDiffService;
import com.jaezi.bus.purchase.model.Purchase;
import com.jaezi.bus.purchase.service.PurchaseService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.JwtUtil;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 采购订单API
 */
@RestController
@RequestMapping("/bus/purchase")
public class PurchaseApi extends BaseApi {

    private final PurchaseService purchaseService;
    private final SupplyDiffService supplyDiffService;

    public PurchaseApi(PurchaseService purchaseService, SupplyDiffService supplyDiffService) {
        this.purchaseService = purchaseService;
        this.supplyDiffService = supplyDiffService;
    }

    /**
     * 采购订单列表(分页)
     *
     * @param filter 分页对象
     * @return 采购订单列表
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

        return returnObjectResult(purchaseService.getAll(filter));
    }

    /**
     * 采购订单根据id获取
     *
     * @param id ID
     * @return 采购订单详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(purchaseService.getOneById(id));
    }

    /**
     * 采购订单修改
     *
     * @param purchase 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody Purchase purchase) {
        int result = purchaseService.update(purchase);
        if (result > 0) {
            return JsonResult.SUCCESS;
        } else if (result == 0) {
            return new JsonResult(ResponseEnum.FAILURE_UPDATE_REPETITION);
        }
        return JsonResult.FAILURE;
    }

    /**
     * 采购订单根据条件导出excel到浏览器
     *
     * @param filter 对象
     * @return 操作是否成功
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response, HttpServletRequest request,
                       @RequestParam Map<String, String> filter) throws Exception {
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

        purchaseService.export(response, filter);
    }

    /**
     * 采购订单导入
     *
     * @param file
     * @return
     */
//    @PostMapping("/import")
    public JsonResult excelImport(@RequestParam(value = "file") MultipartFile file) {
        purchaseService.excelImport(file);
        return JsonResult.SUCCESS;
    }

    /**
     * 订单导出
     *
     * @param response
     * @return
     */
//    @GetMapping("/template/export")
    public void templateExport(HttpServletResponse response) {
        purchaseService.templateExport(response);
    }

    /**
     * 订单未确认条数
     *
     * @param
     * @return JsonResult
     */
    @GetMapping("/unconfirmed")
    public JsonResult purchaseUnconfirmed( HttpServletRequest request) {
        Map<String, String> filter = new HashMap<>(2);
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return new JsonResult(purchaseService.unconfirmed(filter));
    }

}
