package com.jaezi.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author zhangf
 * @version v1.0
 * @corporation copyright by zhangf
 * @date 2020/9/28  15:02
 * @description
 */
public class IpUtil {

    private static Logger log = LoggerFactory.getLogger(IpUtil.class);

    private static final String UNKNOWN = "unknown";
    private static final String X_FORWARDED_FOR = "x-forwarded-for";
    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    private static final String LOCALHOST = "127.0.0.1";
    private static final String LOOPBACK_ADDRESS = "0:0:0:0:0:0:0:1";




    public static String getIpAddress(HttpServletRequest request) {
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


    /**
     * 根据系统的类型获取本服务器的ip地址
     * InetAddress inet = InetAddress.getLocalHost();
     * 但是上述代码在Linux下返回127.0.0.1。
     * 主要是在linux下返回的是/etc/hosts中配置的localhost的ip地址，
     * 而不是网卡的绑定地址。后来改用网卡的绑定地址，可以取到本机的ip地址：）：
     * @throws UnknownHostException
     */
    public static String getSystemLocalIp(){
        Properties props= System.getProperties();
        //操作系统名称
        String osname=props.getProperty("os.name");
        InetAddress inet = null;
        if(osname.startsWith("Windows")){
             inet = getWindowsLocalIp();
        }else if(osname.equalsIgnoreCase("Linux")){
             inet = getLinuxLocalIp();
        }
        if(null == inet){
           return null;
        }
        return inet.getHostAddress();

    }

    /**
     * 获取window 本地ip地址
     * @return
     */
    private static InetAddress getWindowsLocalIp() {
        InetAddress inet = null;
        try {
            inet = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return inet;
    }
    /**
     *
     * 可能多多个ip地址只获取一个ip地址
     * 获取Linux 本地IP地址
     * @return
     * @throws SocketException
     */
    private static InetAddress getLinuxLocalIp() {
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress ip = null;
        while(netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();
            ip = ni.getInetAddresses().nextElement();
            if( !ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":")==-1) {
                return ip;
            } else {
                ip = null;
            }
        }
        return null;
    }
}
