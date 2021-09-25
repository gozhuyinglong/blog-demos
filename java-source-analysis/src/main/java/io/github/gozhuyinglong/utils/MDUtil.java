package io.github.gozhuyinglong.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要工具箱
 *
 * @author 码农StayUp
 * @date 2021/9/23 0023
 */
public class MDUtil {

    /**
     * MD5 加密
     *
     * @param data 要加密的数据
     * @return 32位十六进制字符串
     */
    public static String MD5(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(data);
            return bytesToHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * MD5 加密
     *
     * @param data 要加密的数据
     * @return 32位十六进制字符串
     */
    public static String MD5(String data) {
        return MD5(data.getBytes());
    }

    /**
     * SHA-1 加密
     *
     * @param data 要加密的数据
     * @return 40位十六进制字符串
     */
    public static String SHA1(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(data);
            return bytesToHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * SHA-1 加密
     *
     * @param data 要加密的数据
     * @return 40位十六进制字符串
     */
    public static String SHA1(String data) {
        return SHA1(data.getBytes());
    }

    /**
     * SHA-224 加密
     *
     * @param data 要加密的数据
     * @return 56位十六进制字符串
     */
    public static String SHA224(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-224");
            byte[] bytes = md.digest(data);
            return bytesToHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * SHA-224 加密
     *
     * @param data 要加密的数据
     * @return 56位十六进制字符串
     */
    public static String SHA224(String data) {
        return SHA224(data.getBytes());
    }

    /**
     * SHA-256 加密
     *
     * @param data 要加密的数据
     * @return 64位十六进制字符串
     */
    public static String SHA256(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(data);
            return bytesToHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * SHA-256 加密
     *
     * @param data 要加密的数据
     * @return 64位十六进制字符串
     */
    public static String SHA256(String data) {
        return SHA256(data.getBytes());
    }

    /**
     * SHA-384 加密
     *
     * @param data 要加密的数据
     * @return 96位十六进制字符串
     */
    public static String SHA384(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            byte[] bytes = md.digest(data);
            return bytesToHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * SHA-384 加密
     *
     * @param data 要加密的数据
     * @return 96位十六进制字符串
     */
    public static String SHA384(String data) {
        return SHA384(data.getBytes());
    }

    /**
     * SHA-512 加密
     *
     * @param data 要加密的数据
     * @return 128位十六进制字符串
     */
    public static String SHA512(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(data);
            return bytesToHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * SHA-512 加密
     *
     * @param data 要加密的数据
     * @return 128位十六进制字符串
     */
    public static String SHA512(String data) {
        return SHA512(data.getBytes());
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder hexValue = new StringBuilder();
        for (byte b : bytes) {
            int val = b & 0xFF;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    public static void main(String[] args) {
        System.out.println("MD5\t\t" + MD5("123456"));
        System.out.println("SHA-1\t" + SHA1("123456"));
        System.out.println("SHA-224\t" + SHA224("123456"));
        System.out.println("SHA-256\t" + SHA256("123456"));
        System.out.println("SHA-384\t" + SHA384("123456"));
        System.out.println("SHA-512\t" + SHA512("123456"));
    }
}
