package com.my.core.util;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

public class TotpUtil {

    public static String genSecretKey() {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        return key.getKey();
    }

    public static Integer getTotpPassword(String secret, Long TimeStepSize) {
        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder builder = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder();
        builder.setTimeStepSizeInMillis(TimeStepSize);
        GoogleAuthenticator gAuth = new GoogleAuthenticator(builder.build());
        return gAuth.getTotpPassword(secret);
    }

    public static Boolean authorize(String secret, Integer pwd) {
        return new GoogleAuthenticator().authorize(secret, pwd);
    }

    public static Boolean authorize(String secret, Integer pwd, Long TimeStepSize) {
        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder builder = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder();
        builder.setTimeStepSizeInMillis(TimeStepSize);
        GoogleAuthenticator gAuth = new GoogleAuthenticator(builder.build());
        return gAuth.authorize(secret, pwd);
    }

    /**
     * 建立Google Authenticator密鑰uri
     *
     * @param secret 密鑰字串
     * @return Google Authenticator密鑰uri
     */
    public static String createGoogleAuthenticatorKeyUri(String secret) {
        String otpType = "totp";
        String account = "ABC:Liam@abc.com";
        String issuer = "ABC";
        String googleAuthenticatorKeyUriFormat = "otpauth://%s/%s?secret=%s&issuer=%s";
        return String.format(googleAuthenticatorKeyUriFormat, otpType, account, secret, issuer);
    }
}
