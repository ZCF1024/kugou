package com.zcf.music.high.web.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @User zcf
 * @Create 2018/7/22 8:21
 * @Desc 对url进行编码、解码
 */
public class URLEncodeUtil {

    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    /**
     * URL 编码， Encode默认为 UTF-8
     */
    public static String encode(String url) {
        try {
            return URLEncoder.encode(url, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * URL解码， Decod默认为 UTF-8
     */
    public static String decode(String url) {
        try {
            return URLDecoder.decode(url, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(("Unsupported Encoding Exception"), e);
        }
    }

    public static void main(String[] args) {
        String url = "http://www.baidu.com";
        String encode = encode(url);
        System.out.println("加密前： " + url);
        System.out.println("加码后： " + encode);
        System.out.println("解密后： " + decode(encode));
    }
}