package com.jaezi.common.util;

import org.jasypt.encryption.StringEncryptor;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/15 14:37
 * @description 用于对Spring配置文件中的敏感信息进行加密
 */
public class Aes128Encrypt implements StringEncryptor {

    @Override
    public String encrypt(String value) {
        try {
            return Aes128Codec.encrypt(value);
        } catch (Exception e) {
            e.printStackTrace();
            return value;
        }
    }

    @Override
    public String decrypt(String value) {
        try {
            return Aes128Codec.decrypt(value);
        } catch (Exception e) {
            e.printStackTrace();
            return value;
        }
    }
}