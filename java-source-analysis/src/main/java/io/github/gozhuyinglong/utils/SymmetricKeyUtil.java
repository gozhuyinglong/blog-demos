package io.github.gozhuyinglong.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 对称加密工具箱
 *
 * @author 码农StayUp
 * @date 2021/10/13 0013
 */
public class SymmetricKeyUtil {

    /**
     * DES 算法名称
     */
    private static final String DES_ALGORITHM = "DES";
    /**
     * AES 算法名称
     */
    private static final String AES_ALGORITHM = "AES";
    /**
     * DES 算法的转换类型（算法名称/工作模式/填充方式）
     */
    private static final String DES_TRANSFORMATION = "DES/ECB/PKCS5Padding";
    /**
     * AES 算法的转换类型（算法名称/工作模式/填充方式）
     */
    private static final String AES_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    /**
     * DES 初始化向量，固定8位字节
     */
    private static final String DES_IV_PARAMETER = "12345678";
    /**
     * AES 初始化向量，固定16位字节
     */
    private static final String AES_IV_PARAMETER = "1234567812345678";

    /**
     * 通过密码和算法获取 Key 对象
     *
     * @param key       密钥
     * @param algorithm 算法，例如：AES (128)、DES (56)、DESede (168)、HmacSHA1、HmacSHA256
     * @return 密钥 Key
     * @throws Exception
     */
    private static Key getKey(byte[] key, String algorithm) throws Exception {
        // 通过算法获取 KeyGenerator 对象
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        // 使用密钥做为随机数，初始化 KeyGenerator 对象
        keyGenerator.init(new SecureRandom(key));
        // 生成 Key
        return keyGenerator.generateKey();
    }

    /**
     * DES 加密
     *
     * @param data 原始数据
     * @param key  密钥
     * @return 密文
     */
    private static byte[] encryptDES(byte[] data, byte[] key) throws Exception {
        // 获取 DES Key
        Key secretKey = getKey(key, DES_ALGORITHM);

        // 通过标准转换获取 Cipher 对象, 由该对象完成实际的加密操作
        Cipher cipher = Cipher.getInstance(DES_TRANSFORMATION);
        // 通过加密模式、密钥，初始化 Cipher 对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // 生成密文
        return cipher.doFinal(data);
    }

    /**
     * DES 加密
     *
     * @param data 原始数据
     * @param key  密钥
     * @return 密文（Base64）
     */
    public static String encryptDES(String data, String key) {
        try {
            byte[] bytes = encryptDES(data.getBytes(), key.getBytes());
            return new String(Base64.getEncoder().encode(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES 解密
     *
     * @param data 密文
     * @param key  密钥
     * @return 原始数据
     */
    private static byte[] decryptDES(byte[] data, byte[] key) throws Exception {
        // 获取 DES Key
        Key secretKey = getKey(key, DES_ALGORITHM);
        // 通过标准转换获取 Cipher 对象, 由该对象完成实际的加密操作
        Cipher cipher = Cipher.getInstance(DES_TRANSFORMATION);
        // 通过加密模式、密钥，初始化 Cipher 对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // 生成密文
        return cipher.doFinal(data);
    }

    /**
     * DES 解密
     *
     * @param data 密文
     * @param key  密钥
     * @return 原始数据
     */
    public static String decryptDES(String data, String key) {
        try {
            byte[] bytes = Base64.getDecoder().decode(data.getBytes());
            return new String(decryptDES(bytes, key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES 加密
     *
     * @param data 原始数据
     * @param key  密钥
     * @return 密文
     * @throws Exception
     */
    private static byte[] encryptAES(byte[] data, byte[] key) throws Exception {
        // 获取 AES Key
        Key secretKey = getKey(key, AES_ALGORITHM);

        // 通过标准转换获取 Cipher 对象, 由该对象完成实际的加密操作
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        // 创建一个 IV 对象（CBC 模式需要使用初始化向量）
        IvParameterSpec iv = new IvParameterSpec(AES_IV_PARAMETER.getBytes());
        // 通过加密模式、密钥和 IV，初始化 Cipher 对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        // 生成密文
        return cipher.doFinal(data);
    }

    /**
     * AES 加密
     *
     * @param data 原始数据
     * @param key  密钥
     * @return 密文
     */
    public static String encryptAES(String data, String key) {
        try {
            byte[] bytes = encryptAES(data.getBytes(), key.getBytes());
            return new String(Base64.getEncoder().encode(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES 解密
     *
     * @param data 密文
     * @param key  密钥
     * @return 原始数据
     * @throws Exception
     */
    private static byte[] decryptAES(byte[] data, byte[] key) throws Exception {
        // 获取 AES Key
        Key secretKey = getKey(key, AES_ALGORITHM);
        // 通过标准转换获取 Cipher 对象, 由该对象完成实际的加密操作
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        // 创建一个 IV 对象（CBC 模式需要使用初始化向量）
        IvParameterSpec iv = new IvParameterSpec(AES_IV_PARAMETER.getBytes());
        // 通过加密模式、密钥和 IV，初始化 Cipher 对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        // 生成密文
        return cipher.doFinal(data);
    }

    /**
     * AES 解密
     *
     * @param data 密文
     * @param key  密钥
     * @return 原始数据
     */
    public static String decryptAES(String data, String key) {
        try {
            byte[] bytes = Base64.getDecoder().decode(data.getBytes());
            return new String(decryptAES(bytes, key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String data = "Hello World! 我是：码农StayUp";
        String key = "12345678";

        String cipherTextDES = encryptDES(data, key);
        System.out.println(cipherTextDES);
        System.out.println(decryptDES(cipherTextDES, key));

        String cipherTextAES = encryptAES(data, key);
        System.out.println(cipherTextAES);
        System.out.println(decryptAES(cipherTextAES, key));
    }
}
