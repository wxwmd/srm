package com.jaezi.srminterface.api;

import com.jaezi.common.base.BaseException;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.manager.ThreadManager;
import com.jaezi.srminterface.dao.*;
import com.jaezi.srminterface.service.InterfaceCommService;
import com.jaezi.srminterface.service.InterfaceServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description erp统一请求入口API
 */
@RestController
@RequestMapping("/interface/erp/comm/server")
public class InterfaceApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceApi.class);

    private final InterfaceServer interfaceServer;
    private final InterfaceCommService interfaceCommService;

    public InterfaceApi(InterfaceServer interfaceServer, InterfaceServerDao interfaceServerDao, TableConfigDao tableConfigDao,
                        SqlConfigDao sqlConfigDao, MaterialSupplierDao materialSupplierDao, FiledConfigDao filedConfigDao) {
        this.interfaceServer = interfaceServer;
        this.interfaceCommService = new InterfaceCommService(interfaceServerDao, tableConfigDao, sqlConfigDao, materialSupplierDao, filedConfigDao);
    }

    /**
     * erp统一JSON请求入口
     *
     * @param tableName
     * @param jsonData
     * @return JsonResult
     */
    @PostMapping
    public JsonResult insertInterfaceInfo(@RequestParam(value = "tableName") String tableName,
                                          @RequestParam(value = "jsonData") String jsonData) {
        try {
            interfaceServer.interfaceMain(tableName, jsonData);
        } catch (BaseException e) {
            LOGGER.error("ERP数据导入异常" + e.getMessage());
            return new JsonResult(ResponseEnum.FAILURE.getCode(), e.getMessage());
        }

        return JsonResult.SUCCESS;
    }

    /**
     * erp统一数据excel导入
     *
     * @param file
     * @return
     */
    @PostMapping("/import")
    public JsonResult excelImport(@RequestParam(value = "file") MultipartFile file) {
        return interfaceServer.excelImport(file);
    }

    /**
     * erp统一数据模板导出
     *
     * @param response
     * @return 操作是否成功
     */
    @GetMapping("/template/exports")
    public void export(HttpServletResponse response) {
        interfaceServer.templateExport(response);
    }

    /**
     * erp中间件操作指南导出
     *
     * @param response
     * @return 操作是否成功
     */
    @GetMapping("/docx/export")
    public void docxExport(HttpServletResponse response) {
        interfaceServer.docxExport(response);
    }

    /**
     * erp中间件触发定时任务
     */
    @GetMapping("/refresh")
    public JsonResult refreshDict() {
        ThreadManager.getInstance().syncExecute(() -> {
            interfaceCommService.updateData();
        });
        return JsonResult.SUCCESS;
    }
}
