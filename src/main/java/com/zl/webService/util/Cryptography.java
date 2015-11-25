package com.zl.webService.util;


import org.apache.axiom.om.util.Base64;
import org.apache.geronimo.mail.util.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.MessageDigest;

/**
 * Created by Administrator on 2015/11/25.
 */
public class Cryptography {
    //MD5算法生成摘要
    private static String DIGEST_ALGORITHM = "MD5";
    //3DES加密：CBC模式/使用PKCS5方式填充不足位
    private static String CRYPT_ALGORITHM = "DESede/CBC/PKCS5Padding";
    private static String KEY_ALGORITHM = "DESede";
    //编码格式
    private static String ENCODING_TYPE = "GBK";
    //向量数组
    private static byte[] DEFAULT_IV = {1, 1, 1, 1, 1, 1, 1, 1};

    /**
     * 3DES加密
     * @param input
     * @param strKey
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] input, String strKey) throws Exception {
        Key key = getKey(strKey.getBytes());
        Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
        IvParameterSpec IVSpec = ivGenerator(DEFAULT_IV);
        cipher.init(Cipher.ENCRYPT_MODE, key, IVSpec);
        byte[] output = cipher.doFinal(input);
        return output;
    }

    /**
     * 3DES解密
     * @param input
     * @param strKey
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] input, String strKey) throws Exception {
        Key key = getKey(strKey.getBytes());
        IvParameterSpec IVSpec = ivGenerator(DEFAULT_IV);
        Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, IVSpec);
        byte[] output = cipher.doFinal(input);
        return output;
    }

    /**
     * 生成key
     * @param keyArray
     * @return
     * @throws Exception
     */
    private static Key getKey(byte[] keyArray)
            throws Exception {
        //创建一个空的24位字节数组（默认值为0）
        byte[] keyArrayReturn = new byte[24];
        //将原始字节数组转换为24位
        for (int i = 0; i < keyArray.length && i < keyArrayReturn.length; i++) {
            keyArrayReturn[i] = keyArray[i];
        }
        //生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(keyArrayReturn, KEY_ALGORITHM);
        return key;
    }

    /**
     * 生成加解密向量
     * @param b
     * @return
     * @throws Exception
     */
    private static IvParameterSpec ivGenerator(byte[] b) throws Exception {
        return new IvParameterSpec(b);
    }

    /**
     * 生成摘要
     * @param input
     * @return
     * @throws Exception
     */
    public static String generateDigest(String input) throws Exception {
        MessageDigest DigestGenerator = MessageDigest.getInstance(DIGEST_ALGORITHM);
        DigestGenerator.update(input.getBytes());
        byte[] output = DigestGenerator.digest();
        //摘要转换为16进制，并且大写
        return new String(Hex.encode(output)).toUpperCase();
    }

    /**
     * Base64编码
      * @param b
     * @return
     */
    public static String base64Encode(byte[] b) {
        return Base64.encode(b);
    }

    /**
     * Base64解码
     * @param s
     * @return
     */
    public static byte[] base64Decode(String s) {
        return Base64.decode(s);
    }
}
