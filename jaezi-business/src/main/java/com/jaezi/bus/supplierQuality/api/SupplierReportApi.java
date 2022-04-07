package com.jaezi.bus.supplierQuality.api;

import com.jaezi.bus.supplierQuality.model.SupplierReport;
import com.jaezi.bus.supplierQuality.service.SupplierReportService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import io.minio.MinioClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/14 14:06
 * @description 供应商实验报告和出厂报告API
 */
@RestController
@RequestMapping("/bus/supplier/report")
public class SupplierReportApi extends BaseApi {

    private final SupplierReportService supplierReportService;

    private final MinioClient minioClient;

    public SupplierReportApi(SupplierReportService supplierReportService,MinioClient minioClient) {
        this.supplierReportService = supplierReportService;
        this.minioClient = minioClient;
    }

    /**
     * 供应商实验报告和出厂报告列表(分页)
     *
     * @param filter 分页对象
     * @return 供应商实验报告和出厂报告列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(supplierReportService.findAll(filter));
    }

    /**
     * 供应商实验报告和出厂报告根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(supplierReportService.getOneById(id));
    }

    /**
     * 供应商实验报告和出厂报告添加
     *
     * @param supplierReport 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody SupplierReport supplierReport, HttpServletRequest request) {
        String username = JwtUtil.getUsername(request);
        supplierReport.setSupplierCode(username);
        supplierReport.setReportUploadDate(String.valueOf(System.currentTimeMillis()));
        supplierReport.setReportNumber(String.valueOf(System.currentTimeMillis()));
        return returnIntResult(supplierReportService.add(supplierReport));
    }

    /**
     * 供应商实验报告和出厂报告修改
     *
     * @param supplierReport 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody SupplierReport supplierReport) {
        return returnIntResult(supplierReportService.update(supplierReport));
    }

    /**
     * 供应商实验报告和出厂报告删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(supplierReportService.delete(id));
    }

    /**
     * 供应商实验报告和出厂报告文件下载
     *
     * @param id  要下载的资料对象id
     * @param response response
     */
    @GetMapping("downloadFile")
    public void downloadFile(HttpServletResponse response,HttpServletRequest request, @RequestParam Integer id) {
        supplierReportService.down(response,id);
    }

    /**
     * 供应商实验报告和出厂报告文件上传
     *
     * @param file 上传的文件对象
     * @return 文件的访问路径
     */
    @PostMapping("/upload")
    public JsonResult upload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty() || file.getSize() == 0) {
            return JsonResult.FAILURE;
        }
        String url = supplierReportService.upload(file, minioClient);
        if (ObjectUtils.isEmpty(url)) {
            return JsonResult.FAILURE;
        }
        return new JsonResult(url);
    }
}
