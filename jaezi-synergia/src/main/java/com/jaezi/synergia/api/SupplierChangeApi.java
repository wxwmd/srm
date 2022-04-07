package com.jaezi.synergia.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.synergia.model.SupplierChange;
import com.jaezi.synergia.service.SupplierChangeService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/30 15:39
 * @description 供应商技术、厂址变更API
 */
@RestController
@RequestMapping("/syn/change")
public class SupplierChangeApi extends BaseApi {

    private SupplierChangeService supplierChangeService;

    public SupplierChangeApi(SupplierChangeService supplierChangeService) {
        this.supplierChangeService = supplierChangeService;
    }

    /**
     * 供应商技术、厂址变更列表(分页)
     *
     * @param filter 分页对象
     * @return 公告列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        Integer userType = JwtUtil.getUserType();
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName());
        return returnObjectResult(supplierChangeService.getAll(filter));
    }

    /**
     * 供应商技术、厂址变更根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(supplierChangeService.getOneById(id));
    }

    /**
     * 供应商技术、厂址变更添加
     *
     * @param supplierChange 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody SupplierChange supplierChange) {
        /**
         * todo  ERP提供两个接口
         * 接口1：将添加的信息 通过接口发送到ERP 等待审核
         * 接口2：通过流水号  查询申请的信息状态 更新被系统中技术、厂址变更信息的状态
         * 1、供应商添加 技术、厂址变更信息添加完之后将信息通过接口发送给ERP那边等待审核
         * 2、查询时根据流水号 查询此信息状态再更新本系统状态
         */
        return returnIntResult(supplierChangeService.add(supplierChange));
    }

    /**
     * 供应商技术、厂址变更修改
     *
     * @param supplierChange 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody SupplierChange supplierChange) {
        return returnIntResult(supplierChangeService.update(supplierChange));
    }

    /**
     * 供应商技术、厂址变更删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(supplierChangeService.delete(id));
    }

    /**
     * 供应商技术、厂址变更二级界面文件上传
     *
     * @param file 上传的文件
     * @return JsonResult 文件是否上传成功
     * @author yx
     * @date 2021年8月3日14:25:04
     * @since 1.0
     */
    @PostMapping("/document/upload")
    public JsonResult documentUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return JsonResult.FAILURE;
        }
        String url = supplierChangeService.documentUpload(file);
        if (!ObjectUtils.isEmpty(url)) {
            return new JsonResult(url);
        }
        return JsonResult.FAILURE;
    }

    /**
     * 供应商技术、厂址变更文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    @GetMapping("/download")
    public void down(HttpServletResponse response, @RequestParam Integer id) {
        supplierChangeService.down(response, id);
    }

}
