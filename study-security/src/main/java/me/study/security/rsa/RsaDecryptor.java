package me.study.security.rsa;

import javax.crypto.Cipher;


import java.io.*;
import java.security.*;
import java.security.spec.*;

/**
 * RSA私钥解密
 */
public class RsaDecryptor {
    /**
     * RSA最大解密密文大小
     */
    private static final int    MAX_DECRYPT_BLOCK = 128;

    /**
     * 私钥字符串
     */
    private static final String PRIVATE_KEY_STR   = "";

    private static Cipher       dcipher;

    static {
        try {
            PrivateKey privateKey = loadPrivateKey(PRIVATE_KEY_STR);
            dcipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            dcipher.init(Cipher.DECRYPT_MODE, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] as) throws Exception {
        // 密文字符串
        String encryptedStr = "";
        String str = decrypt(encryptedStr);
        System.out.println(str);
    }


    public static String decrypt(String encryptedStr) throws Exception {
        try {
            return new String(doDecrypt(Base64Utils.decode(encryptedStr)));
        } catch (Exception e) {
            return null;
        }
    }


    private static byte[] doDecrypt(byte[] encryptedData) throws Exception {
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = dcipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = dcipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            offSet += MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }


    /**
     * 从字符串中加载私钥<br>
     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
     * 
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = Base64Utils.decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

}
