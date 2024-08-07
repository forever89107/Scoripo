package com.my.common.utils;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@SuppressWarnings("unused")
public class MD5 {

    private static String encodingCharset = "UTF-8";

    public static String md5(String string) {
        char[] hexDigits =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] bytes = string.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] updateBytes = messageDigest.digest();
            int len = updateBytes.length;
            char[] myChar = new char[len * 2];
            int k = 0;
            for (byte byte0 : updateBytes) {
                myChar[k++] = hexDigits[byte0 >>> 4 & 0x0f];
                myChar[k++] = hexDigits[byte0 & 0x0f];
            }
            return new String(myChar);
        } catch (Exception e) {
            return null;
        }
    }

    public static String sha256(String aValue) {
        aValue = aValue.trim();
        byte[] value;
        try {
            value = aValue.getBytes(encodingCharset);
        } catch (UnsupportedEncodingException e) {
            value = aValue.getBytes();
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return toHex(md.digest(value));

    }

    public static String toHex(byte[] input) {
        if (input == null)
            return null;
        StringBuilder output = new StringBuilder(input.length * 2);
        for (byte b : input) {
            int current = b & 0xff;
            if (current < 16)
                output.append("0");
            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }

    /**
     * ASCII排序
     */
    public static String toAscii(Map<String, String> parameters) {
        List<Map.Entry<String, String>> infoIds = new ArrayList<>(parameters.entrySet());
        // 對所有傳入參數按照字段名的 ASCII 碼從小到大排序（字典序）
        infoIds.sort(Comparator.comparing(Map.Entry::getKey));
        StringBuilder sign = new StringBuilder();
        for (Map.Entry<String, String> item : infoIds) {
            String k = item.getKey();
            if (!StringUtils.hasLength(item.getKey())) {
                Object v = item.getValue();
                if (null != v && !ObjectUtils.isEmpty(v)) {
                    sign.append(k).append("=").append(v).append("&");
                }
            }
        }
        return sign.deleteCharAt(sign.length() - 1).toString();
    }
}
