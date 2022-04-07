package com.jaezi.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by iuyy.net
 * @date 2020/5/19 15:29
 * @description
 */
public final class HttpUtils {

    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String CHARSET_UTF8 = "utf-8";
    private static final int CONNECT_TIMEOUT = 60000;
    private static final int READ_TIMEOUT = 60000;

	private HttpUtils() {}
	
    public static String get(String url) {
        return get(url, null);
    }

    public static String get(String url, Map<String, String> params) {
        return get(url, params, null);
    }

    public static String get(String url, Map<String, String> params, Map<String, String> headers) {
        return execute(new HttpGet(url), url, params, headers);
    }

    public static String post(String url) {
        return post(url, null);
    }

    public static String post(String url, Map<String, String> params) {
        return post(url, params, null);
    }

    public static String post(String url, Map<String, String> params, Map<String, String> headers) {
        return execute(new HttpPost(url), url, params, headers);
    }

    public static String put(String url) {
        return put(url, null);
    }

    public static String put(String url, Map<String, String> params) {
        return put(url, params, null);
    }

    public static String put(String url, Map<String, String> params, Map<String, String> headers) {
        return execute(new HttpPut(url), url, params, headers);
    }

    public static String delete(String url) {
        return delete(url, null);
    }

    public static String delete(String url, Map<String, String> params) {
        return delete(url, params, null);
    }

    public static String delete(String url, Map<String, String> params, Map<String, String> headers) {
        return execute(new HttpDelete(url), url, params, headers);
    }

    private static String handleResponse(HttpResponse response, String charset){
        StatusLine statusLine = response.getStatusLine();
        if(statusLine.getStatusCode() >= HttpStatus.SC_INTERNAL_SERVER_ERROR && statusLine.getStatusCode() <= HttpStatus.SC_INSUFFICIENT_STORAGE){
            throw new RuntimeException("server error:" + statusLine.getStatusCode());
        }
        if(statusLine.getStatusCode() >= HttpStatus.SC_BAD_REQUEST && statusLine.getStatusCode() <= HttpStatus.SC_FAILED_DEPENDENCY){
            throw new RuntimeException("request error:" + statusLine.getStatusCode());
        }
        try {
            return EntityUtils.toString(response.getEntity(), charset);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Http 请求执行
     * @param requestBase 请求
     * @param url URL
     * @param params 参数
     * @param headers 请求头
     * @return 响应结果
     */
    private static String execute(HttpRequestBase requestBase, String url,
                                  Map<String, String> params, Map<String, String> headers) {
        HttpResponse response;
        CloseableHttpClient client = HttpClients.createDefault();
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(CONNECT_TIMEOUT).setConnectTimeout(READ_TIMEOUT).build();
        requestBase.setConfig(requestConfig);
        try {

            if(headers != null && !headers.isEmpty()){
                for(Entry<String, String> entry : headers.entrySet()){
                    requestBase.setHeader(entry.getKey(), entry.getValue());
                }
            }

            if(params == null || params.isEmpty()){
                response = client.execute(requestBase);
                return handleResponse(response, CHARSET_UTF8);
            }

            // POST、PUT、DELETE方法走上面，GET方法走下面else。
            if(requestBase instanceof HttpEntityEnclosingRequestBase){
                return handleResponse(processPPD(requestBase, params, client), CHARSET_UTF8);
            }else{
                return handleResponse(processGet(requestBase, url, params, client), CHARSET_UTF8);
            }

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            requestBase.releaseConnection();
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 处理 POST、PUT、DELETE 请求
     * @param requestBase 请求
     * @param params 参数
     * @param client httpclient
     * @return 响应结果
     * @throws IOException IO异常
     */
    private static HttpResponse processPPD(HttpRequestBase requestBase, Map<String, String> params,
                                           CloseableHttpClient client) throws IOException {

        StringEntity entity = new StringEntity(JSON.toJSONString(params), Charset.forName(CHARSET_UTF8));
        entity.setContentType(CONTENT_TYPE_JSON);
        HttpEntityEnclosingRequestBase enclosingRequestBase = (HttpEntityEnclosingRequestBase)requestBase;
        enclosingRequestBase.setEntity(entity);
        return client.execute(enclosingRequestBase);

    }

    /**
     * 处理 GET 请求
     * @param requestBase 请求
     * @param url 路径
     * @param params 参数
     * @param client httpclient
     * @return 响应结果
     * @throws IOException IO异常
     * @throws URISyntaxException URI异常
     */
    private static HttpResponse processGet(HttpRequestBase requestBase, String url,
                                           Map<String, String> params, CloseableHttpClient client)
            throws IOException, URISyntaxException {

        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Entry<String, String> param : params.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, Charset.forName(CHARSET_UTF8));

        String str = EntityUtils.toString(entity);
        if (url.contains("?")) {
            url = url + '&' + str;
        } else {
            url = url + '?' + str;
        }
        requestBase.setURI(new URI(url));

        return client.execute(requestBase);
    }

    private static class HttpDelete extends HttpEntityEnclosingRequestBase {
        public static final String METHOD_NAME = "DELETE";

        public HttpDelete() {
        }

        public HttpDelete(URI uri) {
            this.setURI(uri);
        }

        public HttpDelete(String uri) {
            this.setURI(URI.create(uri));
        }

        @Override
        public String getMethod() {
            return "DELETE";
        }
    }

}
