package com.jaezi.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author Warren
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/15 14:37
 * @description Cookie工具类
 */
public final class CookieUtil {

    private static Logger logger = LoggerFactory.getLogger(CookieUtil.class);

    private static final String UTF8 = "UTF-8";
    private static final String SITE_PREFIX = "http://";
    private static final int MAX_AGE = 86400;
    private static final String DOT = ".";
    private static final String COLON = ":";
    private static final String SLASH = "/";
    private static final String TOKEN = "token";

    private CookieUtil(){}

    /**
     * 得到Cookie的值
     * @param request 请求
     * @return cookie名对应的cookie值
     */
    public static String getToken(HttpServletRequest request) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(TOKEN)) {
                    retValue = URLDecoder.decode(cookie.getValue(), UTF8);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return retValue;
    }

    /**
     * 删除Cookie带cookie域名
     */
    public static void deleteToken(HttpServletResponse response) {
        String cookieValue = "";
        Cookie cookie = new Cookie(TOKEN, cookieValue);
        response.addCookie(cookie);
    }

    /**
     * 设置Cookie
     * @param request 请求
     * @param response 响应
     * @param cookieValue cookie值
     */
    public static void setToken(HttpServletRequest request, HttpServletResponse response, String cookieValue) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, UTF8);
            }
            Cookie cookie = new Cookie(TOKEN, cookieValue);
            cookie.setMaxAge(MAX_AGE);
            if (null != request) {
                cookie.setDomain(getDomainName(request));
            }
            cookie.setPath(SLASH);
            response.addCookie(cookie);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 得到cookie的域名
     */
    private static String getDomainName(HttpServletRequest request) {
        String domainName;
        String serverName = request.getRequestURL().toString();
        if (isEmpty(serverName)) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            if(serverName.startsWith(SITE_PREFIX)){
                serverName = serverName.substring(7);
            }
            if(serverName.contains(SLASH)){
                serverName = serverName.split(SLASH)[0];
            }
            String[] domains = serverName.split(DOT);
            int len = domains.length;
            if (len > 3 ) {
                // www.xxx.com.cn
                domainName = domains[len - 3] + DOT + domains[len - 2] + DOT + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = domains[len - 2] + DOT + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }
        return domainName.contains(COLON) ? domainName.split(COLON)[0] : domainName;
    }

    private static boolean isEmpty(String data){
        return data == null || data.length() == 0 || data.equals("null");
    }

}
