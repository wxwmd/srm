package com.jaezi.common.util;

import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RSACodec {

    public static final String ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "publicKey";
    private static final String PRIVATE_KEY = "privateKey";
    private static final int KEY_SIZE = 512;

    public class Jar {
        private static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANqwbiB2Ay+0OICZigIE7PP5uma2KYsviDo22o/bGc7zUG5wySV+n7EQFNmZNPklnJfb+82jPC1+dRZeffTcnv0CAwEAAQ==";
        private static final String PRIVATE_KEY = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEA2rBuIHYDL7Q4gJmKAgTs8/m6ZrYpiy+IOjbaj9sZzvNQbnDJJX6fsRAU2Zk0+SWcl9v7zaM8LX51Fl599Nye/QIDAQABAkEAvtszgYj9BukWVsWpOFHsKpFMKM/aOerlzUiKPvpM79q01BtE5TU2wEX9Up+AYX8Az7EOrA0vAV3PKHA6htZRgQIhAO9xsMVdjE9hWCHPRIMKSeiH5IAsr4tgiz7RXyyHhb9pAiEA6c9dWEz1gC2lKV/QzjdDGlByPFFIXBs4R+BY8mZ6hHUCIHF4Dg4pm8FOXyZ+g3gZ2xZvt5AqHPJOyQ32yERg4LfpAiEArWkWI2qY3uN7zu74sCm+hCLMRz5F+8JYgL69WbN2BQ0CIQC1cgJCwhMnl/FBNDHu4aic9y1VqExNncWZwDufe3Igow==";
    }

    /**
     * 用公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return 加密数据
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) {
        try {
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(encodedKeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException |
                InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return 加密数据
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) {
        try {
            PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(encodedKeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException |
                InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return 解密数据
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) {
        try {
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(encodedKeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException |
                InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return 解密数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) {
        try {
            PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(encodedKeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException |
                InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取公钥
     *
     * @param keyMap 密钥Map
     * @return 公钥
     */
    public static byte[] getPublicKey(Map<String, Key> keyMap) {
        Key key = keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * 获取私钥
     *
     * @param keyMap 密钥Map
     * @return 私钥
     */
    public static byte[] getPrivateKey(Map<String, Key> keyMap) {
        Key key = keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 根据字符串生成 PublicKey 。
     * 注意，字符串是根据 initKey 生成的公钥字符串
     *
     * @param key
     * @return
     */
    public static PublicKey getRSAPublidKey(String key) {
        try {
            byte[] keyBytes;
            keyBytes = (new BASE64Decoder()).decodeBuffer(key);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据字符串生成 PrivateKey 。
     * 注意，字符串是根据 initKey 生成的私钥字符串
     *
     * @param key
     * @return
     */
    public static PrivateKey getRSAPrivateKey(String key) {
        try {
            byte[] keyBytes;
            keyBytes = (new BASE64Decoder()).decodeBuffer(key);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 如果用initKey生成了一对钥匙之后，拿着字符串的公钥私钥想真实一步操作的话。
     * Const.PUBLIC_KEY			用initKey生成的公钥
     * Const.PRIVATE_KEY		用initKey生成的私钥
     *
     * @return
     */
    public static Map<String, Key> initKey2() {
        Map<String, Key> keyMap = new ConcurrentHashMap<>();
//        RSAPublicKey publicKey = (RSAPublicKey) getRSAPublidKey(Const.PUBLIC_KEY);
//        RSAPrivateKey privateKey = (RSAPrivateKey) getRSAPrivateKey(Const.PRIVATE_KEY);
//        keyMap.put(PUBLIC_KEY, publicKey);
//        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 如果用initKey生成了一对钥匙之后，拿着字符串的公钥私钥想真实一步操作的话。
     * Const.PUBLIC_KEY			用initKey生成的公钥
     * Const.PRIVATE_KEY		用initKey生成的私钥
     *
     * @return
     */
    public static Map<String, Key> initJarKey() {
        Map<String, Key> keyMap = new ConcurrentHashMap<>();
        RSAPublicKey publicKey = (RSAPublicKey) getRSAPublidKey(Jar.PUBLIC_KEY);
        RSAPrivateKey privateKey = (RSAPrivateKey) getRSAPrivateKey(Jar.PRIVATE_KEY);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static void main(String[] args) {
//        Map<String, Key> stringKeyMap = initKey();
//        System.out.println(stringKeyMap);
        //用私钥加密
//        byte[] privateKey = encryptByPrivateKey("1111".getBytes(StandardCharsets.UTF_8), getPrivateKey(stringKeyMap));
        //用公钥解密
//        byte[] publicKey = decryptByPublicKey(privateKey, getPublicKey(stringKeyMap));
//        System.out.println(new String(privateKey));
//        System.out.println(new String(publicKey));

//        byte[] publicKey = decryptByPublicKey(Base64Codec.decrypt(Base64Codec.encrypt(privateKey)), getPublicKey(stringKeyMap));
//        System.out.println(new String(publicKey));
    }

    /**
     * 自动生成钥匙对，每次都不一样
     *
     * @return
     */
    public static Map<String, Key> initKey() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGen.initialize(KEY_SIZE);
            //生成密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            Map<String, Key> keyMap = new ConcurrentHashMap<>();
            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);
            return keyMap;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
