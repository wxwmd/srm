package com.jaezi.web.config;


import com.jaezi.common.manager.ThreadManager;
import com.jaezi.common.util.HttpUtil;
import com.jaezi.web.JaeziApplication;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.http.client.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.TimerTask;

/**
 * @author warren
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/22 18:31
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JaeziApplication.class)
public class RestTemplateConfigTest extends TestCase {

    @Test
    public void test() {
        System.out.println("123");
    }


    @Test
    public void HttpTest() {
        SimpleClientHttpRequestFactory clientFactory = new SimpleClientHttpRequestFactory();

        // ConnectTimeout只有在网络正常的情况下才有效，因此两个一般都设置
        clientFactory.setConnectTimeout(5000); //建立连接的超时时间  5秒
        clientFactory.setReadTimeout(5000); // 传递数据的超时时间（在网络抖动的情况下，这个参数很有用）
        try {
//            ClientHttpRequest client = simpleClientHttpRequestFactory.createRequest(URI.create("https://www.baidu.com"), HttpMethod.GET);
//            // 发送请求
//            ClientHttpResponse response = client.execute();
//            System.out.println(response.getStatusCode()); //200 OK
//
//            System.out.println(response.getStatusText()); // OK
//
//            System.out.println(response.getHeaders()); //
//
//            // 返回内容 是个InputStream
//            byte[] bytes = FileCopyUtils.copyToByteArray(response.getBody());
//            System.out.println(new String(bytes, StandardCharsets.UTF_8)); // 百度首页内容的html

            RestTemplate restTemplate = new RestTemplate(clientFactory);
            //设置请求头
            restTemplate.setInterceptors(Collections.singletonList(new ClientHttpRequestInterceptor() {
                @Override
                public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                    HttpHeaders headers = request.getHeaders();
                    headers.add("cookie", "justdojava");
                    return execution.execute(request, body);
                }
            }));

//            //get请求 url,返回类型,传参
//            ResponseEntity<String> responseEntity = restTemplate.getForEntity("127.0.0.1:8080, String.class, "");
//            //状态码
//            HttpStatus statusCode = responseEntity.getStatusCode();
//            //返回内容
//            String body = responseEntity.getBody();
////            System.out.println(body); // 百度首页内容的html
//
//            ResponseEntity<String> responseEntity1 = restTemplate.postForEntity("https://www.baidu.com", null, String.class);
//            System.out.println(responseEntity1.getBody()); // 百度首页内容的html;
//
//            restTemplate.put("https://www.baidu.com", null);
//
//            restTemplate.delete("https://www.baidu.com", 99);
//            MultiValueMap map = new LinkedMultiValueMap();
//            map.add("name", "1111");
//
            //GET方法测试
            MultiValueMap map = new LinkedMultiValueMap();
            map.add("name", "1111");
            ResponseEntity<String> responseEntity = HttpUtil.get("http://127.0.0.1:8080/test?name={name}", String.class, map, restTemplate);
            responseEntity.getBody();

            //POST方法测试
            MultiValueMap map1 = new LinkedMultiValueMap();
            map1.add("name", "1111");
            ResponseEntity<String> responseEntity1 = HttpUtil.post("http://127.0.0.1:8080/test", String.class, map1, restTemplate);
            responseEntity1.getBody();

            //PUT方法测试
            MultiValueMap map2 = new LinkedMultiValueMap();
            map2.add("name", "1111");
            HttpUtil.put("http://127.0.0.1:8080/test", map2, restTemplate);

            //DELETE方法测试
            MultiValueMap map3 = new LinkedMultiValueMap();
            map3.add("name", "1111");
            HttpUtil.delete("http://127.0.0.1:8080/test?name={name}", map3, restTemplate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        Task task = new Task();
        ThreadManager.getInstance().syncExecute(task);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("11111");
            }
        };

        ThreadManager.getInstance().asyncExecute(timerTask);
    }

    public class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("11111");
        }
    }

}