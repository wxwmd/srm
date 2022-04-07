package com.jaezi.srminterface.service;

import com.alibaba.excel.EasyExcel;
import com.jaezi.common.base.BaseException;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.manager.ThreadManager;
import com.jaezi.common.util.FileUtil;
import com.jaezi.srminterface.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 动态数据表服务层
 */

@Service
public class InterfaceService implements InterfaceServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceService.class);

    private InterfaceCommService interfaceCommService;

    private final String templateFilePath = "/template/material.xlsx";
    private final String templateFileName = "material.xlsx";

    private final String docxFilePath = "/template/动态表配置指南.docx";
    private final String docxFileName = "动态表配置指南.docx";

    @Autowired
    private void setInterfaceDao(InterfaceServerDao interfaceServerDao, TableConfigDao tableConfigDao, SqlConfigDao sqlConfigDao, MaterialSupplierDao materialSupplierDao, FiledConfigDao filedConfigDao) {
        interfaceCommService = new InterfaceCommService(interfaceServerDao, tableConfigDao, sqlConfigDao, materialSupplierDao, filedConfigDao);
    }

    @Override
    public void interfaceMain(String tableName, String jsonData) throws BaseException {
        interfaceCommService.tableService(tableName, jsonData);
    }

    @Override
    public JsonResult excelImport(MultipartFile file) {
        String tableName = file.getOriginalFilename().split("[.]")[0];
        try {
            //数据表验证
            InterfaceCheckImportListener interfaceCheckImportListener = new InterfaceCheckImportListener(interfaceCommService, tableName);
            EasyExcel.read(file.getInputStream(), interfaceCheckImportListener).sheet().doRead();
            interfaceCheckImportListener.updateData();
        } catch (BaseException | IOException e) {
            LOGGER.error("ERP数据导入异常" + e.getMessage());
            return new JsonResult(ResponseEnum.FAILURE.getCode(), e.getMessage());
        }
        //创建数据表并录入数据
        ThreadManager.getInstance().syncExecute(() -> {
            try {
                EasyExcel.read(file.getInputStream(), new InterfaceImportListener(interfaceCommService, tableName)).sheet().doRead();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return JsonResult.SUCCESS;
    }

    /**
     * 模板导出
     *
     * @param response
     */
    public void templateExport(HttpServletResponse response) {
        FileUtil.downLoad(this.getClass().getResourceAsStream(templateFilePath), templateFileName, response);
    }

    /**
     * 指南导出
     *
     * @param response
     */
    public void docxExport(HttpServletResponse response) {
        FileUtil.downLoad(this.getClass().getResourceAsStream(docxFilePath), docxFileName, response);
    }

}
