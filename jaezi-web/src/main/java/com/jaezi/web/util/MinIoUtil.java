package com.jaezi.web.util;

import com.google.api.client.util.ByteStreams;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.constant.MinioConst;
import com.jaezi.common.util.RandomUtil;
import com.jaezi.common.util.StringUtil;
import io.minio.MinioClient;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 202/10/16 14:14
 * @description MinIo工具类
 */

@Component
public class MinIoUtil {

    private MinioClient minioClient;

    public MinIoUtil(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 文件上传
     *
     * @param file 上传的文件对象
     * @return 文件的访问路径
     */
    public Map<String, String> upload(MultipartFile file) throws Exception {
        Map<String, String> map = new Hashtable<>();
        String fileName = file.getOriginalFilename();
        if (StringUtil.isNull(fileName)) {
            return null;
        }
        String newFileName = MinioConst.IMAGE_DIR + RandomUtil.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        InputStream inputStream = file.getInputStream();
        minioClient.putObject(MinioConst.STATIC_RESOURCE_BUCKET, newFileName, inputStream, file.getContentType());
        inputStream.close();
        map.put("url", minioClient.getObjectUrl(MinioConst.STATIC_RESOURCE_BUCKET, newFileName));
        long fileSize = file.getSize() / 1024;
        map.put("fileSize", fileSize + "KB");
        return map;
    }

    /**
     * 多文件上传
     *
     * @param request 请求对象
     * @return 文件的访问路径集合
     */
    public JsonResult uploadProductImages(StandardMultipartHttpServletRequest request) throws Exception {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        if (fileMap.size() == 0) {
            return JsonResult.FAILURE;
        }
        List<Map<String, String>> result = new ArrayList<>();
        for (MultipartFile file : fileMap.values()) {
            Map<String, String> map = upload(file);
            if (!ObjectUtils.isEmpty(map)) {
                result.add(map);
            }
        }
        return new JsonResult(result);
    }

    /**
     * 文件下载
     *
     * @param fileUrl  文件路径
     * @param response
     * @throws IOException
     */
    public void downloadFile(String fileUrl, String fileName, HttpServletResponse response) throws Exception {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (StringUtil.isBlank(fileUrl)) {
            throw new Exception("文件下载失败");
        }

        // 获取文件输入流
        InputStream inputStream =
                minioClient.getObject(MinioConst.STATIC_RESOURCE_BUCKET, MinioConst.IMAGE_DIR + fileUrl.substring(fileUrl.lastIndexOf("/") + 1));

        response.setHeader("Content-Disposition", "attachment;fileName=" +
                new String(fileName.getBytes("gb2312"), StandardCharsets.ISO_8859_1));
        response.setContentType("application/multipart/form-data");
        //创建输出流
        OutputStream outputStream = response.getOutputStream();
        ByteStreams.copy(inputStream, outputStream);
        //清空缓存并输出流
//            outputStream.flush();
        // 关闭流
        inputStream.close();
        outputStream.close();

    }

}
