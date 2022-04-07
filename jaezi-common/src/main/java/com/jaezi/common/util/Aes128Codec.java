package com.jaezi.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2019/8/27 15:42
 * @description 对称加密算法AES
 */
public final class Aes128Codec {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "Q5h1gENuzlGwQuOK5a/hGH+GlBEms4DO9sqVniICBSw=";

    private Aes128Codec() {
    }

    /**
     * 加密
     * @param data 待加密数据
     * @return 密文
     */
    public static String encrypt(String data) {
        try {

            Key key = new SecretKeySpec(Base64.decodeBase64(SECRET_KEY), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(data.getBytes());
            return Base64.encodeBase64String(result);

        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                BadPaddingException |
                IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param data 待解密数据
     * @return 明文
     */
    public static String decrypt(String data) {
        try {

            Key key = new SecretKeySpec(Base64.decodeBase64(SECRET_KEY), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(Base64.decodeBase64(data));
            return new String(result);

        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                BadPaddingException |
                IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String username= "JAEZI_COM";
//        String password = "5cb19893aa6b46fda86c146f14937a64e3956009";
        String password = "123456";
        String encryptUsername = encrypt(username);
        String encryptPassword = encrypt(password);
        System.out.println("加密的用户名："+encryptUsername);
        System.out.println("加密的密码："+encryptPassword);
        System.out.println("解密的用户名："+decrypt(encryptUsername));
        System.out.println("解密的密码："+decrypt(encryptPassword));
//        String fileUrl = "http://192.168.2.11:8686/static/supplierlierReport/2021-08-24/3365d0499bab450da6e79d708dae2e55.docx";
//        String sub = fileUrl.substring(0, fileUrl.lastIndexOf("/"));
//        System.out.println(fileUrl.substring(fileUrl.lastIndexOf(".")+1));
//        System.out.println(sub.substring(sub.lastIndexOf("/") + 1)+"/"+fileUrl.substring(fileUrl.lastIndexOf("/") + 1));
    }
}
