package com.jaezi.bus.supplierQuality.api;

import com.jaezi.bus.supplierQuality.dto.SupplierQualityInformationDto;
import com.jaezi.bus.supplierQuality.service.SupplierQualityInformationService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.JwtUtil;
import io.minio.MinioClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/20 17:30
 * @description 供应商质量信息API
 */
@RestController
@RequestMapping("/bus/supplier/quality/information")
public class SupplierQualityInformationApi extends BaseApi {

    private final SupplierQualityInformationService supplierQualityInformationService;

    private final MinioClient minioClient;

    public SupplierQualityInformationApi(SupplierQualityInformationService supplierQualityInformationService,MinioClient minioClient) {
        this.supplierQualityInformationService = supplierQualityInformationService;
        this.minioClient = minioClient;
    }

    /**
     * 供应商质量信息列表(分页)
     *
     * @param filter 分页对象
     * @return 供应商质量信息api层列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        Integer userId = JwtUtil.getUserId(request);
        filter.put("userId", String.valueOf(userId));
        filter.put("userType", String.valueOf(userType));
        return returnObjectResult(supplierQualityInformationService.findAll(filter));
    }

    /**
     * 供应商质量信息根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(supplierQualityInformationService.getOneById(id));
    }

    /**
     * 供应商质量信息添加
     *
     * @param supplierQualityInformationDto 包含供应商质量信息数据和可见人员的数据
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody SupplierQualityInformationDto supplierQualityInformationDto) {
        List<Integer> visiblePersonList = supplierQualityInformationDto.getVisiblePersonList();
        Integer visibleType = supplierQualityInformationDto.getVisible();
        if (visiblePersonList.size()>0 || visibleType!=null){
            return returnIntResult(supplierQualityInformationService.addSupplierQualityInformation(supplierQualityInformationDto));
        }
        return new JsonResult(ResponseEnum.REPORT_NOT_HAVE_VISIBLE);
    }

    /**
     * 供应商质量信息修改
     *
     * @param supplierQualityInformationDto 包含供应商质量信息和可见人员的数据
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody SupplierQualityInformationDto supplierQualityInformationDto) {
        List<Integer> visiblePersonList = supplierQualityInformationDto.getVisiblePersonList();
        Integer visibleType = supplierQualityInformationDto.getVisible();
        if (visiblePersonList.size() > 0 || visibleType != null){
            return new JsonResult(supplierQualityInformationService.updateSupplierQualityInformation(supplierQualityInformationDto));
        }
        return new JsonResult(ResponseEnum.REPORT_NOT_HAVE_VISIBLE);
    }

    /**
     * 供应商质量信息删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(supplierQualityInformationService.deleteSupplierQualityInformation(id));
    }

    /**
     * 供应商质量信息文件下载
     *
     * @param id  要下载的资料对象id
     * @param response response
     */
    @GetMapping("downloadFile")
    public void downloadFile(HttpServletResponse response, @RequestParam Integer id) {
        supplierQualityInformationService.down(response,id);
    }

    /**
     * 供应商质量信息文件上传
     *
     * @param file 上传的文件对象
     * @return 文件的访问路径
     */
    @PostMapping("/upload")
    public JsonResult upload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty() || file.getSize() == 0) {
            return JsonResult.FAILURE;
        }
        String result = supplierQualityInformationService.upload(file, minioClient);
        if (ObjectUtils.isEmpty(result)) {
            return JsonResult.FAILURE;
        }
        return new JsonResult(result);
    }
}
