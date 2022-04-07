package com.jaezi.common.util;

import com.google.api.client.util.ByteStreams;
import com.jaezi.common.base.BaseException;
import com.jaezi.common.constant.MinioConst;
import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.jaezi.common.constant.MinioConst.*;
import static com.jaezi.common.util.DateUtil.getCurrentDate;

/**
 * @author warren
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/23 17:50
 * @description
 */
public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 文件上传
     *
     * @param file        上传文件
     * @param minioClient MinioClient
     * @return k->v
     * <url,文件访问路径>
     * <fileSize,文件大小>
     * @author warren
     * @date 2021/7/23
     * @since 1.0
     */
    public static Map<String, String> upload(MultipartFile file, MinioClient minioClient) {
        // 文件夹名称
        String dir = IMAGE_DIR + getCurrentDate() + File.separator;
        return uploadAndFileSiz(file, minioClient, IMAGE_DIR);
    }


    /**
     * 文件上传
     *
     * @param file        文件
     * @param minioClient minioClient
     * @param directory   图片存放的文件夹名称
     * @return String>
     * k->v
     * <url,文件访问路径>
     * <fileSize,文件大小>
     * @author warren
     * @date 2021/7/26
     * @since 1.0
     */
    public static Map<String, String> uploadAndFileSiz(MultipartFile file, MinioClient minioClient, String directory) {
        Map<String, String> map = new HashMap<>();
        String fileName = file.getOriginalFilename();
        if (StringUtil.isNull(fileName)) {
            return null;
        }
        map.put(URL, upload(file, minioClient, directory));
        long fileSize = file.getSize() / 1024;
        map.put(FILE_SIZE, fileSize + UNIT_KB);
        map.put(FILE_NAME, fileName);
        return map;
    }

    /**
     * 文件上传
     *
     * @param file        文件
     * @param minioClient minioClient
     * @param directory   图片存放的文件夹名称
     * @return String> 文件访问路径
     * @author warren
     * @date 2021/7/26
     * @since 1.0
     */
    public static String upload(MultipartFile file, MinioClient minioClient, String directory) {
        String fileName = file.getOriginalFilename();
        if (StringUtil.isNull(fileName)) {
            return null;
        }
        String newFileName = directory + RandomUtil.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        try {
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(MinioConst.STATIC_RESOURCE_BUCKET, newFileName, inputStream, file.getContentType());
            inputStream.close();
            return minioClient.getObjectUrl(MinioConst.STATIC_RESOURCE_BUCKET, newFileName);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    /**
     * 文件下载
     *
     * @param fileUrl  文件路径
     * @param response 请求返回对象
     * @throws IOException
     */
    public static void downLoad(String fileUrl, String fileName, HttpServletResponse response, MinioClient minioClient, String filePrefix) {
        if (StringUtil.isBlank(fileUrl)) {
            return;
        }
        // 获取文件输入流
        InputStream inputStream = null;
        //创建输出流
        OutputStream outputStream = null;
        try {
            // 获取文件输入流
            String sub = fileUrl.substring(0, fileUrl.lastIndexOf("/"));
            String name =  filePrefix + sub.substring(sub.lastIndexOf("/") + 1) + "/" + fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            inputStream = minioClient.getObject(MinioConst.STATIC_RESOURCE_BUCKET, name);
//            inputStream = minioClient.getObject(MinioConst.STATIC_RESOURCE_BUCKET, MinioConst.SUPPLIER_REPORT_DIR + fileUrl.substring(fileUrl.lastIndexOf("/") + 2));
            response.setHeader("Access-Control-Expose-Headers", "Content-disposition");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/multipart/form-data");

            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            //创建输出流
            outputStream = response.getOutputStream();
            ByteStreams.copy(inputStream, outputStream);
            //清空缓存并输出流
//            outputStream.flush();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (!ObjectUtils.isEmpty(inputStream)) {
                    inputStream.close();
                }
                if (!ObjectUtils.isEmpty(outputStream)) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * 文件下载
     *
     * @param inputStream 文件路径
     * @param response    请求返回对象
     * @throws IOException
     */
    public static void downLoad(InputStream inputStream, String fileName, HttpServletResponse response) {
        if (ObjectUtils.isEmpty(inputStream)) {
            return;
        }
        //创建输出流
        OutputStream outputStream = null;
        try {
            response.setHeader("Access-Control-Expose-Headers", "Content-disposition");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/multipart/form-data");

            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            //创建输出流
            outputStream = response.getOutputStream();
            ByteStreams.copy(inputStream, outputStream);
            //清空缓存并输出流
//            outputStream.flush();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (!ObjectUtils.isEmpty(inputStream)) {
                    inputStream.close();
                }
                if (!ObjectUtils.isEmpty(outputStream)) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

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
    public static String fileRead(String path) throws BaseException {
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
