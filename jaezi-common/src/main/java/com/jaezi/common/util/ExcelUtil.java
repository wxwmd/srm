package com.jaezi.common.util;

import com.alibaba.excel.EasyExcel;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/23 17:50
 * @description excel工具具类
 */

public class ExcelUtil {

    /**
     * 导出excel到浏览器
     *
     * @param response  response对象
     * @param list      数据集合
     * @param fileName  文件名
     * @param sheetName 工作表名称
     * @param head      Class对象
     * @return void
     * @date 2021/8/6
     */
    public static void export(HttpServletResponse response, List<?> list, String fileName, String sheetName, Class head) throws Exception {
        response.setHeader("Access-Control-Expose-Headers", "Content-disposition");
        response.setContentType("application/vnd.ms-excel");  // excel 的响应头
        response.setCharacterEncoding("utf-8");
        // 文件名字
        fileName = URLEncoder.encode(fileName, "UTF-8") + ".xlsx";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        EasyExcel.write(response.getOutputStream(), head).sheet(sheetName).doWrite(list);
    }

}
