package com.jaezi.synergia.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.synergia.dto.FrequentlyUsedDataDto;
import com.jaezi.synergia.service.FrequentlyUsedDataService;
import com.jaezi.system.service.UserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/30 15:39
 * @description 常用资料信息API
 */
@RestController
@RequestMapping("/syn/frequently")
public class FrequentlyUsedDataApi extends BaseApi {

    private final FrequentlyUsedDataService frequentlyUsedDataService;

    public FrequentlyUsedDataApi(FrequentlyUsedDataService frequentlyUsedDataService) {
        this.frequentlyUsedDataService = frequentlyUsedDataService;
    }

    /**
     * 常用资料列表(分页)
     *
     * @param filter 分页对象
     * @return 常用资料列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer personId = JwtUtil.getUserId(request);
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userId", String.valueOf(personId));
        filter.put("userType", String.valueOf(userType));
        return returnObjectResult(frequentlyUsedDataService.findAll(filter));
    }

    /**
     * 常用资料根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(frequentlyUsedDataService.getOneById(id));
    }

    /**
     * 常用资料添加
     *
     * @param frequentlyUsedDataDto 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody FrequentlyUsedDataDto frequentlyUsedDataDto) {
        List<Integer> visiblePersonList = Optional.ofNullable(frequentlyUsedDataDto.getVisiblePersonList()).orElse(new ArrayList<>());
        Integer visibleType = frequentlyUsedDataDto.getVisible();
        if (visiblePersonList.size() > 0 || visibleType != null) {
            return returnIntResult(frequentlyUsedDataService.addFrequentlyUsedData(frequentlyUsedDataDto));
        }
        return new JsonResult(ResponseEnum.REPORT_NOT_HAVE_VISIBLE);
    }

    /**
     * 常用资料修改
     *
     * @param frequentlyUsedDataDto 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody FrequentlyUsedDataDto frequentlyUsedDataDto) {
        return returnIntResult(frequentlyUsedDataService.updateUsedData(frequentlyUsedDataDto));
    }

    /**
     * 常用资料删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(frequentlyUsedDataService.delete(id));
    }

    /**
     * 常用资料文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    @GetMapping("/download")
    public void down(HttpServletResponse response, @RequestParam Integer id, String fileName) {
        frequentlyUsedDataService.down(response, id, fileName);
    }

    /**
     * 常用资料文件上传
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
        String url = frequentlyUsedDataService.upload(file);
        if (!ObjectUtils.isEmpty(url)) {
            return new JsonResult(url);
        }
        return JsonResult.FAILURE;
    }

    /**
     * 常用资料二级界面文件上传
     *
     * @param file 上传的文件
     * @return JsonResult 文件是否上传成功
     * @author yx
     * @date 2021年8月3日14:25:04
     * @since 1.0
     */
    @PostMapping("/document/upload")
    public JsonResult documentUpload(@RequestParam(value = "file") MultipartFile file, Integer id) {
        if (file.isEmpty() || file.getSize() == 0 || id == null) {
            return JsonResult.FAILURE;
        }
        String url = frequentlyUsedDataService.documentUpload(file, id);
        if (!ObjectUtils.isEmpty(url)) {
            return new JsonResult(url);
        }
        return JsonResult.FAILURE;
    }
}
