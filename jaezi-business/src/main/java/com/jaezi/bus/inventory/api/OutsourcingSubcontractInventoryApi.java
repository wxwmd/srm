package com.jaezi.bus.inventory.api;

import com.jaezi.bus.inventory.model.DirectInputInformation;
import com.jaezi.bus.inventory.model.OutsourcingSubcontractInventory;
import com.jaezi.bus.inventory.service.DirectInputInformationService;
import com.jaezi.bus.inventory.service.OutsourcingSubcontractInventoryService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/9 16:34
 * @description 外协分包库存Api层
 */
@RestController
@RequestMapping("/bus/outsourcing/subcontract/inventory")
public class OutsourcingSubcontractInventoryApi extends BaseApi {

    private final OutsourcingSubcontractInventoryService outsourcingSubcontractInventoryService;

    public OutsourcingSubcontractInventoryApi(OutsourcingSubcontractInventoryService outsourcingSubcontractInventoryService) {
        this.outsourcingSubcontractInventoryService = outsourcingSubcontractInventoryService;
    }

    /**
     * 外协分包库存列表(分页)
     *
     * @param filter 分页对象
     * @return 外协分包库存列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(outsourcingSubcontractInventoryService.findAll(filter));
    }

    /**
     * 外协分包库存根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(outsourcingSubcontractInventoryService.getOneById(id));
    }

    /**
     * 外协分包库存添加
     *
     * @param outsourcingSubcontractInventory 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody OutsourcingSubcontractInventory outsourcingSubcontractInventory) {
        return returnIntResult(outsourcingSubcontractInventoryService.add(outsourcingSubcontractInventory));
    }

    /**
     * 外协分包库存修改
     *
     * @param outsourcingSubcontractInventory 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody OutsourcingSubcontractInventory outsourcingSubcontractInventory) {
        return returnIntResult(outsourcingSubcontractInventoryService.update(outsourcingSubcontractInventory));
    }

    /**
     * 外协分包库存删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(outsourcingSubcontractInventoryService.delete(id));
    }

    /**
     * 外协分包库存根据条件导出excel到浏览器
     *
     * @param
     * @return 操作是否成功
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response, HttpServletRequest request,
                       @RequestParam Map<String, String> filter) throws Exception {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        outsourcingSubcontractInventoryService.export(response, filter);
    }


}
