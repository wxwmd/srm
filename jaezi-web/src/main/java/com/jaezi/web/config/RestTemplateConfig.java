package com.jaezi.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author warren
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/22 18:17
 * @description
 */
@Configuration
public class RestTemplateConfig{

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory requestFactory) {
        return new RestTemplate(requestFactory);
    }

    /**
     * 简单请求工厂
     * @since 1.0
     * @author warren
     * @date 2021/7/22
     * @param
     * @return ClientHttpRequestFactory
     *
     */
    @Primary
    @Bean
    public ClientHttpRequestFactory simpleClient() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //单位为ms
        factory.setReadTimeout(5000);
        //单位为ms
        factory.setConnectTimeout(5000);
        return factory;
    }

}
