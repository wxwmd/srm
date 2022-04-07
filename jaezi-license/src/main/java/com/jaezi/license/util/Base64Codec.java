package com.jaezi.license.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2019/8/27 15:42
 * @description Base64加密算法
 */
public final class Base64Codec {

    private Base64Codec() {
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @return 密文
     */
    public static byte[] encrypt(String data) {
        return Base64.getMimeEncoder().encode(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @return 密文
     */
    public static String encrypt(byte[] data) {
        return Base64.getMimeEncoder().encodeToString(data);
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @return 明文
     */
    public static byte[] decrypt(String data) {
        return Base64.getMimeDecoder().decode(data);
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @return 明文
     */
    public static byte[] decrypt(byte[] data) {
        return Base64.getMimeDecoder().decode(data);
    }

}
