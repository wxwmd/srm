package com.jaezi.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/5/18 11:36
 * @description 获取MAC地址工具类
 */
public class MacUtil {

    public static String getMac() {
        StringBuffer sb = new StringBuffer();
        try {
            InetAddress ia = InetAddress.getLocalHost();

            //获取网卡，获取地址
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    sb.append("0" + str);
                } else {
                    sb.append(str);
                }
            }
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
        return sb.toString().toUpperCase();
    }

}
