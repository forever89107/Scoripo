package com.my.demo.controller;

import com.my.core.util.TotpUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = "/_scoripo/totp", produces = MediaType.APPLICATION_JSON_VALUE)
public class TotpController {
    private static String secret; // 儲存的密鑰

    /**
     * 取得帶有Google Authenticator驗證器密鑰的qrcode
     *
     * @return 帶有Google Authenticator驗證氣密鑰的qrcode url
     */
    @GetMapping("/auth/qrcode")
    public RedirectView getSecretQrCode() {

        secret = TotpUtil.genSecretKey(); // 產生並儲存密鑰

        String qrCodeData = TotpUtil.createGoogleAuthenticatorKeyUri(secret);
        System.out.println(qrCodeData); // otpauth://totp/ABC:john@@abc.com?secret=CIIHTWBCP7AA6TXT&issuer=ABC

        String googleChartsQrCodeFormat = "https://www.google.com/chart?chs=200x200&cht=qr&chl=%s";
        String qrCodeUrl = String.format(googleChartsQrCodeFormat, qrCodeData);
        System.out.println(qrCodeUrl); // https://www.google.com/chart?chs=200x200&cht=qr&chl=otpauth://totp/ABC:john@@abc.com?secret=CIIHTWBCP7AA6TXT&issuer=ABC

        return new RedirectView(qrCodeUrl); // 重新導向到指定的url
    }

    /**
     * Google Authenticator TOTP驗證
     *
     * @param code 一次性驗證碼
     * @return 驗證結果
     */
    @GetMapping("/auth/{code}")
    @ResponseBody
    public String googleAuthenticatorAuth(@PathVariable("code") int code) {
        return TotpUtil.authorize(secret, code) ? "pass" : "fail";
    }
}
