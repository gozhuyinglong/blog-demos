package io.github.gozhuyinglong.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * 编解码工具箱
 *
 * @author 码农StayUp
 * @date 2021/10/8 0008
 */
public class CodeUtil {

    /**
     * Base64 编码
     *
     * @param data 要编码的数据
     * @return
     */
    public static String encodeBase64(String data) {
        return encodeBase64(data, Charset.defaultCharset());
    }

    /**
     * Base64 编码
     *
     * @param data    要编码的数据
     * @param charset 字符集
     * @return
     */
    public static String encodeBase64(String data, Charset charset) {
        byte[] binaryData = Base64.getEncoder().encode(data.getBytes(charset));
        return new String(binaryData);
    }

    /**
     * Base64 解码
     *
     * @param data 要解码的数据
     * @return
     */
    public static String decodeBase64(String data) {
        return decodeBase64(data, Charset.defaultCharset());
    }

    /**
     * Base64 解码
     *
     * @param data    要解码的数据
     * @param charset 字符集
     * @return
     */
    public static String decodeBase64(String data, Charset charset) {
        byte[] binaryData = Base64.getDecoder().decode(data.getBytes());
        return new String(binaryData, charset);
    }

    /**
     * URL 编码
     *
     * @param data 要编码的数据
     * @return
     */
    public static String encodeURL(String data) {
        return encodeURL(data, Charset.defaultCharset());
    }

    /**
     * URL 编码
     *
     * @param data    要编码的数据
     * @param charset 字符集
     * @return
     */
    public static String encodeURL(String data, Charset charset) {
        try {
            return URLEncoder.encode(data, charset.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * URL 解码
     *
     * @param data 要解码的数据
     * @return
     */
    public static String decodeURL(String data) {
        return decodeURL(data, Charset.defaultCharset());
    }

    /**
     * URL 解码
     *
     * @param data    要解码的数据
     * @param charset 字符集
     * @return
     */
    public static String decodeURL(String data, Charset charset) {
        try {
            return URLDecoder.decode(data, charset.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(encodeBase64("码农StayUp"));
        System.out.println(decodeBase64("56CB5YacU3RheVVw"));

        System.out.println(encodeBase64("码农StayUp", Charset.forName("GBK")));
        System.out.println(decodeBase64("wuvFqVN0YXlVcA==", Charset.forName("GBK")));

        System.out.println(encodeURL("http://www.baidu.com?name=码农StayUp"));
        System.out.println(decodeURL("http%3A%2F%2Fwww.baidu.com%3Fname%3D%E7%A0%81%E5%86%9CStayUp"));

        System.out.println(encodeURL("http://www.baidu.com?name=码农StayUp", Charset.forName("GBK")));
        System.out.println(decodeURL("http%3A%2F%2Fwww.baidu.com%3Fname%3D%C2%EB%C5%A9StayUp", Charset.forName("GBK")));
    }
}
