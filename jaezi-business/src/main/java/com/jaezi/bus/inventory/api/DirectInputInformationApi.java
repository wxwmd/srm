package com.jaezi.bus.inventory.api;

import com.jaezi.bus.inventory.model.DirectInputInformation;
import com.jaezi.bus.inventory.service.DirectInputInformationService;
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
 * @date 2021/8/9 16:30
 * @description 直送入库信息API
 */
@RestController
@RequestMapping("/bus/direct/information")
public class DirectInputInformationApi extends BaseApi {

    private final DirectInputInformationService directInputInformationService;

    public DirectInputInformationApi(DirectInputInformationService directInputInformationService) {
        this.directInputInformationService = directInputInformationService;
    }

    /**
     * 直送入库信息列表(分页)
     *
     * @param filter 分页对象
     * @return 直送入库信息列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(directInputInformationService.findAll(filter));
    }

    /**
     * 直送入库信息根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(directInputInformationService.getOneById(id));
    }

    /**
     * 直送入库信息添加
     *
     * @param directInputInformation 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody DirectInputInformation directInputInformation) {
        return returnIntResult(directInputInformationService.add(directInputInformation));
    }

    /**
     * 直送入库信息修改
     *
     * @param directInputInformation 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody DirectInputInformation directInputInformation) {
        return returnIntResult(directInputInformationService.update(directInputInformation));
    }

    /**
     * 直送入库信息删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(directInputInformationService.delete(id));
    }

    /**
     * 直送入库信息根据条件导出excel到浏览器
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
        directInputInformationService.export(response, filter);
    }
}
