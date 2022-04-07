package com.jaezi.system.service;

import com.alibaba.excel.EasyExcel;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.manager.ThreadManager;
import com.jaezi.common.util.FileUtil;
import com.jaezi.system.dao.MaterialDao;
import com.jaezi.system.dao.UserDao;
import com.jaezi.system.model.Material;
import com.jaezi.system.vo.MaterialVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/5/19 19:13
 * @description 物料逻辑层
 */

@Service
public class MaterialService extends BaseService<Material, MaterialVo> {

    private MaterialDao materialDao;
    private UserDao userDao;

    @Autowired
    public void setBaseDao(MaterialDao materialDao, UserDao userDao) {
        super.setBaseDao(materialDao);
        this.materialDao = materialDao;
        this.userDao = userDao;
    }

    /**
     * 物料导入
     *
     * @param file
     * @return
     */
    public void materielImport(MultipartFile file) {
        ThreadManager.getInstance().syncExecute(() -> {
            try {
                EasyExcel.read(file.getInputStream(), Material.class, new MaterialImportListener(materialDao, userDao)).sheet().doRead();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private final String filePath = "/template/mat.xlsx";
    private final String fileName = "mat.xlsx";

    /**
     * 物料模板导出
     *
     * @param response
     * @return
     */
    public void templateExport(HttpServletResponse response) {
        FileUtil.downLoad(this.getClass().getResourceAsStream(filePath), fileName, response);
    }
}
