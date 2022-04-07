package com.jaezi.synergia.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.common.util.FileUtil;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.synergia.dao.SupplierChangeDao;
import com.jaezi.synergia.model.Aggregat;
import com.jaezi.synergia.model.SupplierChange;
import com.jaezi.synergia.model.TechnicalData;
import com.jaezi.synergia.vo.SupplierChangeVo;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.jaezi.common.constant.MinioConst.*;
import static com.jaezi.common.util.DateUtil.getCurrentDate;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 供应商技术、厂址变更逻辑层
 */

@Service
public class SupplierChangeService extends BaseService<SupplierChange, SupplierChangeVo> {

    private SupplierChangeDao supplierChangeDao;

    private final MinioClient minioClient;

    @Autowired
    public void setBaseDao(SupplierChangeDao supplierChangeDao) {
        super.setBaseDao(supplierChangeDao);
        this.supplierChangeDao = supplierChangeDao;
    }

    public SupplierChangeService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 添加供应商技术、厂址
     *
     * @param supplierChange
     * @return int
     */
    @Override
    public int add(SupplierChange supplierChange) {
        supplierChange.setCreateTime(System.currentTimeMillis());
        HttpServletRequest request = JwtUtil.getRequest();
        String token = request.getHeader("Credential");
        supplierChange.setSupplierCode(JwtUtil.getUsername(token));
        return supplierChangeDao.add(supplierChange);
    }

    /**
     * 更新供应商技术、厂址
     *
     * @param supplierChange
     * @return int
     */
    @Override
    public int update(SupplierChange supplierChange) {
        supplierChange.setCreateTime(System.currentTimeMillis());
        return supplierChangeDao.update(supplierChange);
    }

    /**
     * 更新供应商技术、厂址二级界面文件上传
     *
     * @param file 要上传的文件
     * @return String    上传的文件保存的地址路径
     * @author yx
     * @date 2021/9/1
     * @since 1.0
     */
    public String documentUpload(MultipartFile file) {
        String dir = SUPPLIER_CHANGE + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            return result.get(URL);
        }
        return null;
    }

    /**
     * 文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    public void down(HttpServletResponse response, Integer id) {
        SupplierChange oneById = supplierChangeDao.getOneById(id);
        if (oneById != null) {
            String url = oneById.getDocumentUrl();
            String suffix = url.substring(url.lastIndexOf(".") + 1);
            FileUtil.downLoad(oneById.getDocumentUrl(), oneById.getDocumentName() + "." + suffix, response, minioClient, SUPPLIER_CHANGE);
        }
    }

}
