package com.my.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
@Slf4j
public class AesEncryptUtils {

    // Can be configured in the Constant class, and read from the configuration file, 16 characters, custom defined
    private static final String KEY = "YOUR_KEY";

    // Parameters represent Algorithm Name / Encryption Mode / Data Padding Method
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * Encrypt
     */
    public static String encrypt(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        byte[] b = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        // Use Base64 algorithm to encode, to avoid garbled characters for Chinese
        return Base64.getUrlEncoder().encodeToString(b);
    }

    /**
     * Decrypt
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        // Use Base64 algorithm to decode, to avoid garbled characters for Chinese
        byte[] encryptBytes = Base64.getUrlDecoder().decode(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String encrypt(String content) throws Exception {
        return encrypt(content, KEY);
    }

    public static String decrypt(String encryptStr) throws Exception {
        return decrypt(encryptStr, KEY);
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("merchant", "XXX");
        map.put("customerID", "1056_XX");
        map.put("token", "token");
        map.put("client", "PC");
        String content = (new ObjectMapper()).writeValueAsString(map);
        System.out.println("Before encryption: " + content);

        String encrypt = encrypt(content, KEY);
        System.out.println("After encryption: " + encrypt);

        String decrypt = decrypt(encrypt, KEY);
        System.out.println("After decryption: " + decrypt);
    }
}
