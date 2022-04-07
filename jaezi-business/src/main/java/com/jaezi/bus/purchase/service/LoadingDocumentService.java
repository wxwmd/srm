package com.jaezi.bus.purchase.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.purchase.dao.LoadingDocumentDao;
import com.jaezi.bus.purchase.dao.LoadingRecordDao;
import com.jaezi.bus.purchase.dto.LoadingDocumentRecordDto;
import com.jaezi.bus.purchase.model.LoadingDocument;
import com.jaezi.bus.purchase.model.LoadingRecord;
import com.jaezi.bus.purchase.vo.LoadingDocumentVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.util.FileUtil;
import io.minio.MinioClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static com.jaezi.common.constant.MinioConst.LOADING_DOCUMENT;
import static com.jaezi.common.util.DateUtil.getCurrentDate;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/5 17:36
 * @description 装货单的业务层
 */
@Service
public class LoadingDocumentService extends BaseService<LoadingDocument, LoadingDocumentVo> {

    private LoadingDocumentDao loadingDocumentDao;

    private LoadingRecordDao loadingRecordDao;

    private final MinioClient minioClient;

    public LoadingDocumentService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Autowired
    public void setBaseDao(LoadingDocumentDao loadingDocumentDao, LoadingRecordDao loadingRecordDao) {
        super.setBaseDao(loadingDocumentDao);
        this.loadingDocumentDao = loadingDocumentDao;
        this.loadingRecordDao = loadingRecordDao;
    }

    /**
     * 获取所有装货单信息
     *
     * @param filter
     *  过滤条件：
     *  1、工厂  factory
     *  2、收货窗口  receivingWindow
     *  3、装运说明  shippingExplain
     *  4、装车日期  loadingDate
     *  5、车牌号  loadingNumber
     *
     * @return LoadingDocument>
     */
    public DataGrid<LoadingDocument> findAll(Map<String, String> filter) {
        DataGrid<LoadingDocument> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<LoadingDocument> all = loadingDocumentDao.findAll(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<LoadingDocument> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<LoadingDocument> list = loadingDocumentDao.findAll(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    /**
     * 根据传入的装车单和记录信息，添加对应的装车单信息和记录信息
     *
     * @param loadingDocumentRecordDto 装车单记录对象
     * @return int  添加成功数
     * @author whj
     * @date 2021/8/23
     * @since 1.0
     */
    public int addDocumentAndRecord(LoadingDocumentRecordDto loadingDocumentRecordDto) {
        LoadingDocument loadingDocument = new LoadingDocument();
        BeanUtils.copyProperties(loadingDocumentRecordDto, loadingDocument);
        int result = loadingDocumentDao.add(loadingDocument);
        List<LoadingRecord> loadingRecords = loadingDocumentRecordDto.getLoadingRecords();
        if (result > 0) {
            if (loadingRecords.size() > 0) {
                for (LoadingRecord loadingRecord : loadingRecords) {
                    loadingRecord.setLoadingDocumentId(loadingDocument.getId());
                    loadingRecordDao.add(loadingRecord);
                }
            }
        }
        return result;
    }

    /**
     * 根据装车单id获取装车单详情
     *
     * @param loadingDocumentId 装车单id
     * @return LoadingDocumentVo  装车单详情对象
     * @author whj
     * @date 2021/8/24
     * @since 1.0
     */
    public LoadingDocumentVo getLoadingDocumentDetails(Integer loadingDocumentId) {
        return loadingDocumentDao.getLoadingDocumentDetails(loadingDocumentId);
    }

    /**
     * 装车单采购查询(分页)
     *
     * @param filter
     * @return LoadingDocument
     */
    public DataGrid<LoadingDocument> getLoadingPurchase(Map<String, String> filter, List<String> purchaseIdList) {
        DataGrid<LoadingDocument> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<LoadingDocument> all = loadingRecordDao.getLoadingPurchase(filter, purchaseIdList);
            dg.setRecords(all);
            return dg;
        }
        Page<LoadingDocument> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<LoadingDocument> list = loadingRecordDao.getLoadingPurchase(filter, purchaseIdList);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    /**
     * 文件上传
     *
     * @param file 上传的文件
     * @return String  上传文件保存的路径
     * @author yx
     * @date 2021/9/1
     * @since 1.0
     */
    public Map<String, String> upload(MultipartFile file) {
        String dir = LOADING_DOCUMENT + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            return result;
        }
        return null;
    }
}
