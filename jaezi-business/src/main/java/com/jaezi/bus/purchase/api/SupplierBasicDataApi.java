package com.jaezi.bus.purchase.api;

import com.jaezi.bus.purchase.model.SupplierBasicData;
import com.jaezi.bus.purchase.service.SupplierBasicDataService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 供应商基础数据API
 */
@RestController
@RequestMapping("/bus/supplier")
public class SupplierBasicDataApi extends BaseApi {

    private final SupplierBasicDataService supplierBasicDataService;

    public SupplierBasicDataApi(SupplierBasicDataService supplierBasicDataService) {
        this.supplierBasicDataService = supplierBasicDataService;
    }

    /**
     * 供应商基础数据列表(分页)
     *
     * @param filter 分页对象
     * @return 装车单打印列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(supplierBasicDataService.getAll(filter));
    }

    /**
     * 供应商基础数据根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(supplierBasicDataService.getOneById(id));
    }

    /**
     * 供应商基础数据修改
     *
     * @param supplierBasicData 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody SupplierBasicData supplierBasicData) {
        return returnIntResult(supplierBasicDataService.update(supplierBasicData));
    }

}
