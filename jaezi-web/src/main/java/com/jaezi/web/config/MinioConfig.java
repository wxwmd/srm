package com.jaezi.web.config;

import com.jaezi.common.constant.MinioConst;
import com.jaezi.common.util.Aes128Codec;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/07/21 14:20
 * @description
 */
@Configuration
public class MinioConfig {

    @Value("${minio.url}")
    private String url;

    @Value("${minio.username}")
    private String username;

    @Value("${minio.password}")
    private String password;

    private static final Logger LOG = LoggerFactory.getLogger(MinioConfig.class);

    @Bean
    public MinioClient minioClient(){
        MinioClient minioClient = null;
        try {
            minioClient = new MinioClient(url, Aes128Codec.decrypt(username), Aes128Codec.decrypt(password));
            if (!minioClient.bucketExists(MinioConst.STATIC_RESOURCE_BUCKET)) {
                minioClient.makeBucket(MinioConst.STATIC_RESOURCE_BUCKET);
                minioClient.setBucketPolicy(MinioConst.STATIC_RESOURCE_BUCKET, "*", PolicyType.READ_WRITE);
            }
            if (!minioClient.bucketExists(MinioConst.IMAGE_BUCKET)) {
                minioClient.makeBucket(MinioConst.IMAGE_BUCKET);
                minioClient.setBucketPolicy(MinioConst.IMAGE_BUCKET, "*", PolicyType.READ_WRITE);
            }
            if (!minioClient.bucketExists(MinioConst.VIDEO_BUCKET)) {
                minioClient.makeBucket(MinioConst.VIDEO_BUCKET);
                minioClient.setBucketPolicy(MinioConst.VIDEO_BUCKET, "*", PolicyType.READ_WRITE);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return minioClient;
    }
}
