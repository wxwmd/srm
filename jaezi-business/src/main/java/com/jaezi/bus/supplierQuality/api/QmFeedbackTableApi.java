package com.jaezi.bus.supplierQuality.api;

import com.jaezi.bus.supplierQuality.dto.QmFeedbackTableDto;
import com.jaezi.bus.supplierQuality.service.QmFeedbackTableService;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/14 14:31
 * @description QM反馈单API
 */
@RestController
@RequestMapping("/bus/qm/feedback/table")
public class QmFeedbackTableApi extends BaseApi {

    private final QmFeedbackTableService qmFeedbackTableService;

    private final MinioClient minioClient;

    public QmFeedbackTableApi(QmFeedbackTableService qmFeedbackTableService,MinioClient minioClient) {
        this.qmFeedbackTableService = qmFeedbackTableService;
        this.minioClient = minioClient;
    }

    /**
     * 未审批和已审批的QM反馈单列表(分页)
     *
     * @param filter 分页对象
     * @return 未审批和已审批的QM反馈单api层列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        Integer userId = JwtUtil.getUserId(request);
        filter.put("userId", String.valueOf(userId));
        filter.put("userType", String.valueOf(userType));
        return returnObjectResult(qmFeedbackTableService.findAll(filter));
    }

    /**
     * QM反馈单根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(qmFeedbackTableService.getOneById(id));
    }

    /**
     * QM反馈单添加
     *
     * @param qmFeedbackTableDto 包含QM反馈单数据和可见人员的数据
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody QmFeedbackTableDto qmFeedbackTableDto) {
        List<Integer> visiblePersonList = qmFeedbackTableDto.getVisiblePersonList();
        qmFeedbackTableDto.setFeedbackSubmitDate(String.valueOf(LocalDate.now()));
        Integer visibleType = qmFeedbackTableDto.getVisible();
        if (visiblePersonList.size()>0 || visibleType!=null){
        return returnIntResult(qmFeedbackTableService.addQmFeedbackTable(qmFeedbackTableDto));
        }
        return new JsonResult(ResponseEnum.REPORT_NOT_HAVE_VISIBLE);
    }

    /**
     * QM反馈单修改
     *
     * @param qmFeedbackTableDto 包含QM反馈单数据和可见人员的数据
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody QmFeedbackTableDto qmFeedbackTableDto) {
        List<Integer> visiblePersonList = qmFeedbackTableDto.getVisiblePersonList();
        Integer visibleType = qmFeedbackTableDto.getVisible();
        if (visiblePersonList.size() > 0 || visibleType != null){
            return new JsonResult(qmFeedbackTableService.updateQmFeedbackTable(qmFeedbackTableDto));
        }
        return new JsonResult(ResponseEnum.REPORT_NOT_HAVE_VISIBLE);
    }

    /**
     * QM反馈单删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(qmFeedbackTableService.deleteQmFeedback(id));
    }

    /**
     * QM反馈单文件下载
     *
     * @param id  要下载的资料对象id
     * @param response response
     */
    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response, @RequestParam Integer id) {
        qmFeedbackTableService.down(response,id);
    }

    /**
     * QM反馈单文件上传
     *
     * @param file 上传的文件对象
     * @return 文件的访问路径
     */
    @PostMapping("/upload")
    public JsonResult upload(@RequestParam(value = "file") MultipartFile file) {
        String result = qmFeedbackTableService.upload(file, minioClient);
        if (ObjectUtils.isEmpty(result)) {
            return JsonResult.FAILURE;
        }
        return new JsonResult(result);
    }
}
