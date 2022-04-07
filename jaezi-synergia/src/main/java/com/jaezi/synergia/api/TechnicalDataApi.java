package com.jaezi.synergia.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.synergia.dto.TechnicalDataDto;
import com.jaezi.synergia.dto.TechnicalDataUrlDto;
import com.jaezi.synergia.model.TechnicalData;
import com.jaezi.synergia.service.TechnicalDataService;
import com.jaezi.system.service.UserService;
import io.minio.MinioClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 技术图档资料API
 */
@RestController
@RequestMapping("/syn/technical")
public class TechnicalDataApi extends BaseApi {

    private final TechnicalDataService technicalDataService;

    public TechnicalDataApi(TechnicalDataService technicalDataService) {
        this.technicalDataService = technicalDataService;
    }

    /**
     * 技术图档资料列表(分页)
     *
     * @param filter 分页对象
     * @return 公告列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType();
        String token = request.getHeader("Credential");
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName());
        filter.put("userId", String.valueOf(JwtUtil.getUserId(token)));
        return returnObjectResult(technicalDataService.findAll(filter));
    }

    /**
     * 技术图档资料根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(technicalDataService.getOneById(id));
    }

    /**
     * 技术图档资料添加
     *
     * @param technicalData 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody TechnicalData technicalData) {
        return returnIntResult(technicalDataService.add(technicalData));
    }

    /**
     * 技术图档资料修改
     *
     * @param technicalDataDto 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody TechnicalDataDto technicalDataDto) {
        return returnIntResult(technicalDataService.updateTechnicalData(technicalDataDto));
    }

    /**
     * 技术图档资料删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(technicalDataService.delete(id));
    }

    /**
     * 技术图档资料文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    @GetMapping("/download")
    public void down(@RequestParam Integer id, HttpServletResponse response, String fileName) {
        technicalDataService.down(response, id, fileName);
    }

    /**
     * 技术图档资料文件上传
     *
     * @param file 上传的文件
     * @return JsonResult 文件是否上传成功
     * @author yx
     * @date 2021年8月3日14:25:04
     * @since 1.0
     */
    @PostMapping("/upload")
    public JsonResult upload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return JsonResult.FAILURE;
        }
        String url = technicalDataService.upload(file);
        if (!ObjectUtils.isEmpty(url)) {
            return new JsonResult(url);
        }
        return JsonResult.FAILURE;
    }

    /**
     * 公共多文件上传
     *
     * @param request 请求对象
     * @return 文件的访问路径集合
     */
    @PostMapping(value = "/upload/multi")
    public JsonResult uploadProductImages(StandardMultipartHttpServletRequest request) {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        if (fileMap.size() == 0) {
            return JsonResult.FAILURE;
        }
        List<Map<String, String>> result = new ArrayList<>();
        for (MultipartFile file : fileMap.values()) {
            Map<String, String> map = technicalDataService.batchUpload(file);
            if (!ObjectUtils.isEmpty(map)) {
                result.add(map);
            }
        }
        return new JsonResult(result);
    }

    /**
     * 批量更新技术图档
     *
     * @return 文件的访问路径集合
     */
    @PutMapping(value = "/batch/update/technicalData")
    public JsonResult batchUpdateTechnicalData(@RequestBody TechnicalDataUrlDto technicalDataUrlDto) {
        List<Integer> visiblePersonList = technicalDataUrlDto.getVisiblePersonList();
        Integer visibleType = technicalDataUrlDto.getVisible();
        List<Map<String, String>> fileNames = technicalDataUrlDto.getFileNames();
        if (visiblePersonList.size()>0 || visibleType!=null ){
            List<String> list = technicalDataService.batchUpdateTechnicalData(fileNames, visibleType, visiblePersonList);
            if (list.size()>0){
                return new JsonResult(432,"上传失败的文件",list);
            }
            return JsonResult.SUCCESS;
        }
        return JsonResult.FAILURE;
    }

    /**
     * 技术图档资料二级界面文件上传
     *
     * @param file 上传的文件
     * @return JsonResult 文件是否上传成功
     * @author yx
     * @date 2021年8月3日14:25:04
     * @since 1.0
     */
    @PostMapping("/document/upload")
    public JsonResult documentUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty() || file.getSize() == 0) {
            return JsonResult.FAILURE;
        }
        String url = technicalDataService.documentUpload(file);
        if (!ObjectUtils.isEmpty(url)) {
            return new JsonResult(url);
        }
        return JsonResult.FAILURE;
    }
}
