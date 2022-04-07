package com.jaezi.common.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/23 17:50
 * @description http工具类
 */

public class HttpUtil {

    /**
     * get请求
     *
     * @param url          请求路径
     * @param responseType 请求返回类型
     * @param object       请求传入参数
     * @param restTemplate restTemplate对象
     * @throws
     */
    public static ResponseEntity get(String url, Class responseType, Object object, RestTemplate restTemplate) {
        return restTemplate.getForEntity(url, responseType, object);
    }

    /**
     * post请求
     *
     * @param url          请求路径
     * @param responseType 请求返回类型
     * @param object       请求传入参数
     * @param restTemplate restTemplate对象
     * @throws
     */
    public static ResponseEntity post(String url, Class responseType, Object object, RestTemplate restTemplate) {
        return restTemplate.postForEntity(url, object, responseType);
    }

    /**
     * put请求
     *
     * @param url          请求路径
     * @param object       请求传入参数
     * @param restTemplate restTemplate对象
     * @throws
     */
    public static void put(String url, Object object, RestTemplate restTemplate) {
        restTemplate.put(url, object);
    }

    /**
     * delete请求
     *
     * @param url          请求路径
     * @param object       请求传入参数
     * @param restTemplate restTemplate对象
     * @throws
     */
    public static void delete(String url, Object object, RestTemplate restTemplate) {
        restTemplate.delete(url, object);
    }

}
