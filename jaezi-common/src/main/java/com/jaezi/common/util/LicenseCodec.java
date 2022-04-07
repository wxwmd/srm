package com.jaezi.common.util;

import com.alibaba.fastjson.JSON;
import com.jaezi.common.base.BaseException;
import org.springframework.util.ObjectUtils;

import java.security.Key;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2019/8/27 15:42
 * @description License加密算法
 */
public class LicenseCodec {

    /**
     * 生成公钥私钥
     *
     * @return 密文
     */
    public static void encrypt1() {
        //获取密钥对
        Map<String, Key> stringKeyMap = RSACodec.initKey();
        //获取私钥
        String base64PrivateKey = Base64Codec.encrypt(RSACodec.getPrivateKey(stringKeyMap));
        FileUtil.fileWrite(System.getProperty("user.dir") + "/privateKey", base64PrivateKey);
        //获取公钥
        String base64PublicKey = Base64Codec.encrypt(RSACodec.getPublicKey(stringKeyMap));
        //加密后写入文件
        FileUtil.fileWrite(System.getProperty("user.dir") + "/publicKey", base64PublicKey);
    }

    public static void main(String[] arg) {
        encrypt1();
    }

    /**
     * 解密license
     *
     * @param data 公钥
     * @return
     */
    public static void decrypt(String data) throws BaseException {
        //读取加密后公钥
        String base64PublicKey = FileUtil.fileRead(LicenseCodec.class.getResourceAsStream("/publicKey"));
        //对公钥进行Base64解密
        //用解密后的公钥对lic解密
        byte[] byteJsonData = RSACodec.decryptByPublicKey(Base64Codec.decrypt(data), Base64Codec.decrypt(base64PublicKey));
        String jsonData = new String(Base64Codec.decrypt(byteJsonData));

        //解密完成对数据进行解析,验证
        Map map = JSON.parseObject(jsonData);
        if (ObjectUtils.isEmpty(base64PublicKey) || ObjectUtils.isEmpty(byteJsonData) ||
                DateUtil.localDateIsAfter(LocalDate.now(), DateUtil.stringToLocalDate(map.get("stopDate").toString()))) {
            throw new BaseException("认证未成功");
        }
    }
}
