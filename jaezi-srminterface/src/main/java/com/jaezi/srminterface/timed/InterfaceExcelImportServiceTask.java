package com.jaezi.srminterface.timed;

import com.alibaba.fastjson.JSON;
import com.jaezi.common.config.ScheduledConfig;
import com.jaezi.srminterface.service.InterfaceServer;
import com.jaezi.srminterface.service.TableConfigService;
import org.apache.http.entity.ContentType;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 定时获取模板文件夹中excel生成中间表
 */

@Configuration
public class InterfaceExcelImportServiceTask extends ScheduledConfig {

    private final TableConfigService tableConfigService;
    private final InterfaceServer interfaceServer;

    public InterfaceExcelImportServiceTask(TableConfigService tableConfigService, InterfaceServer interfaceServer) {
        this.tableConfigService = tableConfigService;
        this.interfaceServer = interfaceServer;
    }

    @Override
    protected void processTask() {
        try {
            getFiles(String.valueOf(JSON.parseObject(tableConfigService.getPath()).get("path")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getCron() {
        return tableConfigService.getCron();
    }

    /**
     * 获取文件夹下所有直接下级文件，不包括目录下的子目录的下的文件
     *
     * @param path
     * @return String>
     */
    public void getFiles(String path) throws IOException {
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                //文件名
                String fileName = tempList[i].getName();
                //后缀名
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                //验证文件名
                if (suffix.equals(".xls") || suffix.equals(".xlsx")) {
                    String tableName = fileName.substring(0, fileName.lastIndexOf("."));
                    //验证表名
                    if (!ObjectUtils.isEmpty(tableConfigService.getCommentsByTableName(tableName))) {
                        interfaceServer.excelImport(fileToMultipartFile(tempList[i]));
                    }
                }
            }
        }
    }

    /**
     * File 转 MultipartFile
     *
     * @param file
     * @return MultipartFile
     * @date 2021/10/27
     */
    public MultipartFile fileToMultipartFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        // MockMultipartFile 需引入 spring-test包
        MultipartFile multipartFile = new MockMultipartFile("copy" + file.getName(), file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        return multipartFile;
    }

}
