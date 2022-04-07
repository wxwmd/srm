package com.jaezi.system.service;

import com.alibaba.excel.EasyExcel;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.manager.ThreadManager;
import com.jaezi.common.util.FileUtil;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.system.dao.SupplierDao;
import com.jaezi.system.dao.UserDao;
import com.jaezi.system.dto.SupplierDto;
import com.jaezi.system.model.Supplier;
import com.jaezi.system.vo.SupplierVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 供应商服务接口实现类
 */
@Service
public class SupplierService extends BaseService<Supplier, SupplierVo> {

    private UserDao userDao;
    private SupplierDao supplierDao;

    @Autowired
    public void setBaseDao(UserDao userDao, SupplierDao supplierDao) {
        super.setBaseDao(supplierDao);
        this.supplierDao = supplierDao;
        this.userDao = userDao;
    }

    /**
     * 获取所有供应商
     *
     * @return 对应列表
     */
    public List<Map> getAllSupplier(String type) {
        return supplierDao.getAllSupplier(type, String.valueOf(JwtUtil.getUserType()), JwtUtil.getRealName());
    }

    /**
     * 更新供应商信息
     * @param supplierDto 供应商信息
     * @return
     */
    public int update(SupplierDto supplierDto) {
        supplierDao.updateSupplierPhoneByUserId(supplierDto.getPhone(), supplierDto.getUserId());
        return supplierDao.update(supplierDto);
    }

    /**
     * 供应商导入
     * @param file 文件对象
     * @return
     */
    public void excelImport(MultipartFile file) {
        ThreadManager.getInstance().syncExecute(() -> {
            try {
                EasyExcel.read(file.getInputStream(), SupplierDto.class, new SupplierImportListener(userDao, supplierDao)).sheet().doRead();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private final String filePath = "/template/sup.xlsx";
    private final String fileName = "sup.xlsx";

    /**
     * 供应商模板导出
     * @param response
     * @return
     */
    public void templateExport(HttpServletResponse response) {
        FileUtil.downLoad(this.getClass().getResourceAsStream(filePath), fileName, response);
    }
}
