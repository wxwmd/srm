package com.jaezi.srminterface.service;

import com.jaezi.common.base.BaseException;
import com.jaezi.common.bean.JsonResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 接口访问接口层
 */

public interface InterfaceServer {
    /**
     * 接口访问入口
     *
     * @param tableName
     * @param jsonData
     * @return
     * @throws BaseException
     */
    void interfaceMain(String tableName, String jsonData) throws BaseException;

    JsonResult excelImport(MultipartFile file);

    /**
     * 模板导出
     *
     * @param response
     */
    void templateExport(HttpServletResponse response);

    /**
     * 指南导出
     *
     * @param response
     */
    void docxExport(HttpServletResponse response);
}
