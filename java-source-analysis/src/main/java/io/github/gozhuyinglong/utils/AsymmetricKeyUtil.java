package io.github.gozhuyinglong.utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 非对称加密工具箱
 *
 * @author 码农StayUp
 * @date 2021/10/15 0015
 */
public class AsymmetricKeyUtil {

    private static final String RSA_ALGORITHM = "RSA";

    /**
     * 生成密钥对
     *
     * @param keySize 密钥大小
     * @return
     * @throws Exception
     */
    public static KeyPair generatorRSAKeyPair(int keySize) throws Exception {
        // 根据 RSA 算法，获取密钥对生成器
        KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        // 初始化密钥长度
        generator.initialize(keySize);
        // 生成密钥对
        return generator.generateKeyPair();
    }

    /**
     * 生成密钥对
     *
     * @param keySize 密钥大小
     * @return
     * @throws Exception
     */
    public static KeyPairStr generatorRSAKeyPairStr(int keySize) throws Exception {
        // 生成密钥对
        KeyPair keyPair = generatorRSAKeyPair(keySize);
        // 获取公钥，并转码为字符串
        PublicKey publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        // 获取私钥，并转码为字符串
        PrivateKey privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        // 返回密钥对的字符串
        return new KeyPairStr(publicKeyStr, privateKeyStr);
    }

    /**
     * 获取公钥对象
     *
     * @param publicKeyStr
     * @return
     * @throws Exception
     */
    private static PublicKey getRSAPublicKey(String publicKeyStr) throws Exception {
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePublic(publicKeySpec);
    }

    /**
     * 获取私钥对象
     *
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    private static PrivateKey getRSAPrivateKey(String privateKeyStr) throws Exception {
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePrivate(privateKeySpec);
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     */
    private static String encrypt(String data, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        cipher.update(data.getBytes());
        return Base64.getEncoder().encodeToString(cipher.doFinal());
    }

    /**
     * RSA 公钥加密
     *
     * @param data
     * @param publicKeyStr
     * @return
     */
    public static String encryptRSAPublic(String data, String publicKeyStr) {
        try {
            PublicKey publicKey = getRSAPublicKey(publicKeyStr);
            return encrypt(data, publicKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     */
    private static String decrypt(String data, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);
        cipher.update(Base64.getDecoder().decode(data));
        return new String(cipher.doFinal());
    }

    /**
     * RSA 私钥解密
     *
     * @param data
     * @param privateKeyStr
     * @return
     */
    private static String decryptRSAPrivate(String data, String privateKeyStr) {
        try {
            PrivateKey privateKey = getRSAPrivateKey(privateKeyStr);
            return decrypt(data, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 密钥对字符串类
     */
    public static class KeyPairStr {
        private final String publicKeyStr;
        private final String privateKeyStr;

        public String getPublicKeyStr() {
            return publicKeyStr;
        }

        public String getPrivateKeyStr() {
            return privateKeyStr;
        }

        public KeyPairStr(String publicKeyStr, String privateKeyStr) {
            this.publicKeyStr = publicKeyStr;
            this.privateKeyStr = privateKeyStr;
        }

        @Override
        public String toString() {
            return "KeyPairStr{" +
                    "publicKeyStr='" + publicKeyStr + '\'' +
                    ", privateKeyStr='" + privateKeyStr + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        String data = "Hello World! 我是：码农StayUp";
        KeyPairStr keyPairStr = generatorRSAKeyPairStr(2048);
        System.out.println("公钥：" + keyPairStr.getPublicKeyStr());
        System.out.println("私钥：" + keyPairStr.getPrivateKeyStr());
        String text = encryptRSAPublic(data, keyPairStr.getPublicKeyStr());
        System.out.println("密文：" + text);
        System.out.println("原文：" + decryptRSAPrivate(text, keyPairStr.getPrivateKeyStr()));
    }
}
