package scorpio.core.util;

import org.apache.commons.codec.digest.HmacAlgorithms;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Matrix Secret Registration
 */
public class HMacSha1Util {
    private static final String HMAC_SHA1_ALGORITHM = HmacAlgorithms.HMAC_SHA_1.getName();

    public static String encrypt(String input, String key) {
        try {
            return hmacWithJava(input.getBytes(), key);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String hmacWithJava(byte[] data, String key)
            throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMacSha1Util.HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMacSha1Util.HMAC_SHA1_ALGORITHM);
        mac.init(secretKeySpec);
        return bytes2Hex(mac.doFinal(data));
    }

    //二行制轉字串
    private static String bytes2Hex(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        String hash = formatter.toString();
        formatter.close();
        return hash;
    }
}
