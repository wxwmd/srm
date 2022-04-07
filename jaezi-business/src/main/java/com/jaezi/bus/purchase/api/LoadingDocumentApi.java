package com.jaezi.bus.purchase.api;

import com.jaezi.bus.plan.service.SupplyDiffService;
import com.jaezi.bus.purchase.dto.LoadingDocumentRecordDto;
import com.jaezi.bus.purchase.model.LoadingDocument;
import com.jaezi.bus.purchase.model.LoadingRecord;
import com.jaezi.bus.purchase.service.LoadingDocumentService;
import com.jaezi.bus.purchase.service.LoadingRecordService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.FileUtil;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.common.util.StringUtil;
import io.minio.MinioClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.*;

import static com.jaezi.common.constant.MinioConst.LOADING_DOCUMENT;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/5 17:35
 * @description 装车单API
 */
@RestController
@RequestMapping("/bus/loading/document")
public class LoadingDocumentApi extends BaseApi {

    private final LoadingDocumentService loadingDocumentService;
    private final SupplyDiffService supplyDiffService;
    private final LoadingRecordService loadingRecordService;
    private final MinioClient minioClient;

    public LoadingDocumentApi(LoadingDocumentService loadingDocumentService,
                              SupplyDiffService supplyDiffService,
                              LoadingRecordService loadingRecordService,
                              MinioClient minioClient) {
        this.loadingDocumentService = loadingDocumentService;
        this.supplyDiffService = supplyDiffService;
        this.loadingRecordService = loadingRecordService;
        this.minioClient = minioClient;
    }

    /**
     * 装车单列表(分页)
     *
     * @param filter 分页对象
     * @return 装车单列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(loadingDocumentService.findAll(filter));
    }

    /**
     * 装车单根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(loadingDocumentService.getOneById(id));
    }

    /**
     * 装车单添加
     *
     * @param loadingDocumentRecordDto 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody LoadingDocumentRecordDto loadingDocumentRecordDto, HttpServletRequest request) {
        String username = JwtUtil.getUsername(request);
        loadingDocumentRecordDto.setSupplierCode(username);
        loadingDocumentRecordDto.setLoadingNumber(String.valueOf(System.currentTimeMillis()));
        loadingDocumentRecordDto.setLoadingDocumentDate(String.valueOf(LocalDate.now()));
        return returnIntResult(loadingDocumentService.addDocumentAndRecord(loadingDocumentRecordDto));
    }

    /**
     * 装车单修改
     *
     * @param loadingDocument 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody LoadingDocument loadingDocument) {
        return returnIntResult(loadingDocumentService.update(loadingDocument));
    }

    /**
     * 装车单删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        loadingRecordService.deleteByLoadingDocumentId(id);
        return returnIntResult(loadingDocumentService.delete(id));
    }

    /**
     * 装车单详情查询
     *
     * @param documentId 装车单id
     * @return JsonResult 装车单详情
     * @author whj
     * @date 2021/8/24
     * @since 1.0
     */
    @GetMapping("/{documentId}/details")
    public JsonResult getLoadingDocumentDetailsByDocumentId(@PathVariable Integer documentId) {
        return returnObjectResult(loadingDocumentService.getLoadingDocumentDetails(documentId));
    }

    /**
     * 装车单采购订单查询(分页)
     *
     * @param filter 分页对象
     * @return 装车单采购订单
     */
    @GetMapping("/purchase")
    public JsonResult getLoadingPurchase(@RequestParam(required = false) Map<String, String> filter,
                                         @RequestParam(required = false) String[] purchaseId,
                                         HttpServletRequest request) {
        String username = filter.get("username");
        if (!ObjectUtils.isEmpty(username)) {
            Map<String, Object> userMap = supplyDiffService.getUser(username);
            Map user = (Map) userMap.get(username);

            filter.put("userType", user.get("user_type").toString());
            filter.put("realName", user.get("real_name").toString());
        }

        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));

        List<String> purchaseIdList = new LinkedList<>();
        if (!ObjectUtils.isEmpty(purchaseId)) {
            Collections.addAll(purchaseIdList, purchaseId);
        }
        return returnObjectResult(loadingDocumentService.getLoadingPurchase(filter, purchaseIdList));
    }

    /**
     * 装车单资料上传
     *
     * @param file 上传的文件
     * @return JsonResult 文件是否上传成功
     * @author yx
     * @date 2021年8月3日14:25:04
     * @since 1.0
     */
    @PostMapping("/upload")
    public JsonResult documentUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty() || file.getSize() == 0) {
            return JsonResult.FAILURE;
        }
        Map<String, String> map = loadingDocumentService.upload(file);
        if (!ObjectUtils.isEmpty(map)) {
            return new JsonResult(map);
        }
        return JsonResult.FAILURE;
    }

    /**
     * 文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    @GetMapping("/download")
    public JsonResult down(HttpServletResponse response, @RequestParam Integer id) {
        LoadingRecord oneById = loadingRecordService.getOneById(id);
        if (oneById == null) {
            return JsonResult.FAILURE;
        }
        String url = oneById.getDocumentUrl();
        if (StringUtil.isNull(url)) {
            return new JsonResult(ResponseEnum.FAILURE_DOWNLOAD_FILE_URL_NULL);
        }
        String suffix = url.substring(url.lastIndexOf(".") + 1);
        FileUtil.downLoad(oneById.getDocumentUrl(), oneById.getDocumentName() + "." + suffix, response, minioClient, LOADING_DOCUMENT);
        return JsonResult.SUCCESS;
    }

}
