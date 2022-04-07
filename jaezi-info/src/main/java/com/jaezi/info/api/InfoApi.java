package com.jaezi.info.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.base.BaseException;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.FileUtil;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.info.model.Info;
import com.jaezi.info.service.InfoService;
import com.jaezi.info.vo.InfoVo;
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

import static com.jaezi.common.constant.MinioConst.INFO_COMMUNICATION_DIR;
import static com.jaezi.common.util.DateUtil.getCurrentDate;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 信息交流API
 */
@RestController
@RequestMapping("/info/communication")
public class InfoApi extends BaseApi {

    private final InfoService infoService;
    private final MinioClient minioClient;

    public InfoApi(InfoService infoService, MinioClient minioClient) {
        this.infoService = infoService;
        this.minioClient = minioClient;
    }

    /**
     * 消息信息列表(分页)
     *
     * @param filter 分页对象
     * @return 公告列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(infoService.getAll(filter));
    }

    /**
     * 根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult updateOneById(@PathVariable("id") Integer id, HttpServletRequest request) {
        infoService.updateReadStatus(id, JwtUtil.getRealName(request));
        return returnObjectResult(infoService.getOneById(id));
    }

    /**
     * 添加
     *
     * @param info 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody InfoVo info) throws BaseException {
        return returnIntResult(infoService.addInfo(info));
    }

    /**
     * 修改
     *
     * @param info 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody Info info) {
        return returnIntResult(infoService.update(info));
    }

    /**
     * 删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult remove(@PathVariable Integer id, HttpServletRequest request) {
        return returnIntResult(infoService.remove(id, JwtUtil.getRealName(request)));
    }

    /**
     * 多文件上传
     *
     * @param request 请求对象
     * @return 文件的访问路径集合
     */
    @PostMapping(value = "/upload/batch")
    public JsonResult upload(StandardMultipartHttpServletRequest request) {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        if (fileMap.size() == 0) {
            return JsonResult.FAILURE;
        }
        List<Map<String, String>> result = new ArrayList<>();
        for (MultipartFile file : fileMap.values()) {
            // 文件夹名称
            String dir = INFO_COMMUNICATION_DIR + getCurrentDate() + "/";
            Map<String, String> map = FileUtil.uploadAndFileSiz(file, minioClient, dir);
            if (!ObjectUtils.isEmpty(map)) {
                result.add(map);
            }
        }
        return new JsonResult(result);
    }

    /**
     * 文件下载
     *
     * @param fileUrl  文件路径
     * @param fileName 文件名称
     * @param response response
     */
    @GetMapping("download")
    public void downloadFile(String fileUrl, String fileName, HttpServletResponse response) {
        FileUtil.downLoad(fileUrl, fileName, response, minioClient, INFO_COMMUNICATION_DIR);
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
        String url = infoService.documentUpload(file, id);
        if (!ObjectUtils.isEmpty(url)) {
            return new JsonResult(url);
        }
        return JsonResult.FAILURE;
    }
}
