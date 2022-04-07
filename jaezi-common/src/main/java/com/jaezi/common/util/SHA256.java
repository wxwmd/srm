package com.jaezi.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2019/5/30 9:19
 * @description  哈希加密
 */
public class SHA256 {
    private static final String DEFAULT_SALT = "www.jaezi.com";
    private static final int DEFAULT_TIMES = 7;
    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
    private static MessageDigest digest;
    private static final String DEFAULT_ALGORITHM = "SHA-256";

    public SHA256(){}

    public static String hash(String rawStr) {
        return hash(rawStr, DEFAULT_SALT);
    }

    public static String hash(String rawStr, String salt) {
        return hash(rawStr, salt, DEFAULT_TIMES);
    }

    public static String hash(String rawStr, String salt, int iterationTimes) {
        try {
            digest = MessageDigest.getInstance(DEFAULT_ALGORITHM);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        if(null != salt) {
            digest.reset();
            digest.update(salt.getBytes());
        }
        byte[] data = digest.digest(rawStr.getBytes());
        int times = iterationTimes - 1;
        for(int i = 0; i < times; i++){
            digest.reset();
            data = digest.digest(data);
        }

        return new String(toHex(data));
    }

    private static char[] toHex(byte[] data) {
        char[] buf = new char[data.length << 1];
        int index = 0;
        for (byte b : data) {
            buf[index++] = DIGITS[b >>> 4 & 0xF];
            buf[index++] = DIGITS[b & 0xF];
        }
        return buf;
    }

}
