package com.jaezi.license.util;

import org.apache.commons.lang3.ObjectUtils;

import java.io.*;

/**
 * @author warren
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/23 17:50
 * @description
 */
public class FileUtil {

    /**
     * 写入文件
     *
     * @return
     */
    public static void fileWrite(String path, String data) {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(path));
            writer.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!ObjectUtils.isEmpty(writer)) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过路径读取文件
     *
     * @return
     */
    public static String fileRead(String path) {
        FileInputStream reader = null;
        String read = null;
        try {
            reader = new FileInputStream(path);
            read = fileRead(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (!ObjectUtils.isEmpty(reader)) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return read;
        }
    }

    /**
     * InputStream 读取文件
     *
     * @return
     */
    public static String fileRead(InputStream inputStream) {
        byte[] buf = new byte[1024]; //数据中转站 临时缓冲区
        int len = 0;
        try {
            len = inputStream.read(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!ObjectUtils.isEmpty(inputStream)) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new String(buf, 0, len);
        }
    }
}
