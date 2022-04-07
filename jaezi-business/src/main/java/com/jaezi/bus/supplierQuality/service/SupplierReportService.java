package com.jaezi.bus.supplierQuality.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.supplierQuality.dao.SupplierReportDao;
import com.jaezi.bus.supplierQuality.model.SupplierReport;
import com.jaezi.bus.supplierQuality.vo.SupplierReportVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.util.FileUtil;
import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

import static com.jaezi.common.constant.MinioConst.*;
import static com.jaezi.common.constant.MinioConst.URL;
import static com.jaezi.common.util.DateUtil.getCurrentDate;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/14 14:04
 * @description
 * 供应商实验报告和出厂报告的业务层
 */
@Service
public class SupplierReportService extends BaseService<SupplierReport, SupplierReportVo> {

    private static final Logger log = LoggerFactory.getLogger(SupplierReportService.class);

    private SupplierReportDao supplierReportDao;

    private final MinioClient minioClient;

    @Autowired
    public void setBaseDao(SupplierReportDao supplierReportDao) {
        super.setBaseDao(supplierReportDao);
        this.supplierReportDao = supplierReportDao;
    }

    public SupplierReportService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    public void down(HttpServletResponse response, Integer id) {
        SupplierReport supplierReport = supplierReportDao.getOneById(id);
        if (supplierReport!=null){
            String url = supplierReport.getUrl();
            String suffix = url.substring(url.lastIndexOf(".") + 1);
            FileUtil.downLoad(supplierReport.getUrl(),supplierReport.getReportName()+"."+suffix,response, minioClient, SUPPLIER_REPORT_DIR);
        }
    }

    /**
     * 查询所有供应商报告
     *
     * @param filter
     * @return SupplierQualityInformation>
     * @author whj
     * @date 2021/8/20
     * @since 1.0
     */
    public DataGrid<SupplierReport> findAll(Map<String, String> filter) {
        DataGrid<SupplierReport> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<SupplierReport> all = supplierReportDao.findAll(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<SupplierReport> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<SupplierReport> list = supplierReportDao.findAll(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    /**
     * 文件上传
     * @since 1.0
     * @author whj
     * @date 2021/8/14
     * @param file   文件对象
     * @param minioClient    minio客户端对象
     */
    public String upload(MultipartFile file, MinioClient minioClient) {
        // 文件夹名称
        String dir = SUPPLIER_REPORT_DIR + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result!=null){
            return result.get(URL);
        }
        return null;
    }

}
