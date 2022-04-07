package com.jaezi.license.util;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2019/8/27 15:42
 * @description License加密算法
 */
public class LicenseCodec {

    /**
     * 生成license
     *
     * @param data 待加密数据
     * @return 密文
     */
    public static void encrypt(String data) throws Exception {
        // 读取私钥
//        String privateKey = FileUtil.fileRead(System.getProperty("user.dir") + "/privateKey");
        String privateKey = FileUtil.fileRead(LicenseCodec.class.getResourceAsStream("/privateKey"));

        // 对私钥进行解密,用解密的私钥加密数据生成lic文件
        byte[] lic = RSACodec.encryptByPrivateKey(Base64Codec.encrypt(data), Base64Codec.decrypt(privateKey));
        // lic加密后写入文件
        FileUtil.fileWrite(System.getProperty("user.dir") + "/lic.qy", Base64Codec.encrypt(lic));
    }

}
