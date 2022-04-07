package com.jaezi.log.vo;

import com.jaezi.common.util.StringUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2020/5/30 11:51
 * @description
 */
public class UserAgentUtils {

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    public static String getOsVersion(HttpServletRequest request) {
        return getOsVersion(getUserAgent(request));
    }

    private static String getOsVersion(String userAgent) {
        String osVersion = "";
        if(StringUtil.isBlank(userAgent)) {
            return osVersion;
        }
        String[] strArr = userAgent.substring(userAgent.indexOf("(")+1,
                userAgent.indexOf(")")).split(";");
        if(strArr.length == 0) {
            return osVersion;
        }

        osVersion = strArr[1];
        return osVersion;
    }

    private static OperatingSystem getOperatingSystem(String userAgent) {
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        return agent.getOperatingSystem();
    }

    public static String getOs(HttpServletRequest request) {
        return getOs(getUserAgent(request));
    }

    private static String getOs(String userAgent) {
        return getOperatingSystem(userAgent).getGroup().getName();
    }

    public static String getDeviceType(HttpServletRequest request) {
        return getDeviceType(getUserAgent(request));
    }

    private static String getDeviceType(String userAgent) {
        return getOperatingSystem(userAgent).getDeviceType().toString();
    }

    public static String getOsName(HttpServletRequest request) {
        return getOsName(getUserAgent(request));
    }

    private static String getOsName(String userAgent) {
        return getOperatingSystem(userAgent).getName();
    }

    public static String getDeviceManufacturer(HttpServletRequest request) {
        return getDeviceManufacturer(getUserAgent(request));
    }

    private static String getDeviceManufacturer(String userAgent) {
        return getOperatingSystem(userAgent).getManufacturer().toString();
    }

    private static Browser getBrowser(String agent) {
        return UserAgent.parseUserAgentString(agent).getBrowser();
    }

    public static String getBrowserName(HttpServletRequest request) {
        return getBrowserName(getUserAgent(request));
    }

    private static String getBrowserName(String userAgent) {
        return getBrowser(userAgent).getName();
    }

    public static String getBrowserType(HttpServletRequest request) {
        return getBrowserType(getUserAgent(request));
    }

    private static String getBrowserType(String userAgent) {
        return getBrowser(userAgent).getBrowserType().getName();
    }

    public static String getBrowserGroup(HttpServletRequest request) {
        return getBrowserGroup(getUserAgent(request));
    }

    private static String getBrowserGroup(String userAgent) {
        return getBrowser(userAgent).getGroup().getName();
    }

    public static String getBrowserManufacturer(HttpServletRequest request) {
        return getBrowserManufacturer(getUserAgent(request));
    }

    private static String getBrowserManufacturer(String userAgent) {
        return getBrowser(userAgent).getManufacturer().getName();
    }

    public static String getBorderRenderingEngine(HttpServletRequest request) {
        return getBorderRenderingEngine(getUserAgent(request));
    }

    private static String getBorderRenderingEngine(String userAgent) {
        return getBrowser(userAgent).getRenderingEngine().name();
    }

    public static String getBrowserVersion(HttpServletRequest request) {
        return getBrowserVersion(getUserAgent(request));
    }

    private static String getBrowserVersion(String userAgent) {
        return getBrowser(userAgent).getVersion(userAgent).toString();
    }

    public static String getReallyIp(HttpServletRequest request) {
        String proxyIP = request.getHeader("X-Forwarded-For");
        if (proxyIP == null || proxyIP.length() == 0 || "unknown".equalsIgnoreCase(proxyIP)) {
            proxyIP = request.getHeader("X-Real-IP");
        }
        if (proxyIP == null || proxyIP.length() == 0 || "unknown".equalsIgnoreCase(proxyIP)) {
            proxyIP = request.getHeader("Proxy-Client-IP");
        }
        if (proxyIP == null || proxyIP.length() == 0 || "unknown".equalsIgnoreCase(proxyIP)) {
            proxyIP = request.getHeader("WL-Proxy-Client-IP");
        }
        if (proxyIP == null || proxyIP.length() == 0 || "unknown".equalsIgnoreCase(proxyIP)) {
            proxyIP = request.getHeader("HTTP_CLIENT_IP");
        }
        if (proxyIP != null && proxyIP.length() > 0) {
            String[] ips = proxyIP.split(",");
            return ips[0];
        }else{
            return request.getRemoteAddr();
        }
    }

    public static void main(String[] args) {
//		String androidUserAgent = "Mozilla/5.0 (Linux; Android 8.0; LON-AL00 Build/HUAWEILON-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044204 Mobile Safari/537.36 V1_AND_SQ_7.7.8_908_YYB_D QQ/7.7.8.3705 NetType/WIFI WebP/0.3.0 Pixel/1440";
//		String iosUserAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16A366 QQ/7.7.8.421 V1_IPH_SQ_7.7.8_1_APP_A Pixel/750 Core/UIWebView Device/Apple(iPhone 6s) NetType/WIFI QBWebViewType/1";
        String winUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";

        System.out.println("浏览器组："+getBrowserGroup(winUserAgent));
        System.out.println("浏览器名字："+getBrowserName(winUserAgent));
        System.out.println("浏览器类型"+getBrowserType(winUserAgent));
        System.out.println("浏览器生产商："+getBrowserManufacturer(winUserAgent));
        System.out.println("浏览器版本："+getBrowserVersion(winUserAgent));
        System.out.println("设备生产厂商:"+getDeviceManufacturer(winUserAgent));
        System.out.println("设备类型:"+getDeviceType(winUserAgent));
        System.out.println("设备操作系统："+getOs(winUserAgent));
        System.out.println("操作系统的名字："+getOsName(winUserAgent));
        System.out.println("操作系统的版本号："+getOsVersion(winUserAgent));
        System.out.println("操作系统浏览器的渲染引擎:"+getBorderRenderingEngine(winUserAgent));
    }
}
