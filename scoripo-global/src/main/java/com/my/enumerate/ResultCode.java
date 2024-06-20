package com.my.enumerate;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(200, "SUCCESS"),
    DATA_ERROR(400, "請求資料錯誤"),
    VIRTUAL_REQUEST_ERROR(10, "網絡故障無法打開頁面"),
    FAIL(400, "訪問失敗"),
    ACCESS_ERROR(403, "訪問受限"),
    TOOMANY_REQUEST(429, "請求頻率太高"),
    SERVER_ERROR(500, "服務器錯誤"),
    ILLEGAL_REQUEST(502, "非法請求"),
    FAIL_DEFAULT(501, "未知錯誤"),
    PARAM_ERROR(1001, "參數錯誤"),
    CURRENCY_NOT_SUPPORT(1002, "幣種不支持"),
    REPEAT_LIMIT_ERROR(1003, "5s內重覆提交，請稍後重試"),
    GUESSING_BET_VERIFY_FAIL(1004, "參數檢查不通過"),
    UNAUTHORIZED(401, "簽名錯誤"),
    APP_VERSION_LOW(402, "APP版本過低"),
    TOEKNUNVALIBLE(405, "登錄失效"),
    API_LOST_WHITELIST_CONFIG(303, "您請求的API無白名單配置"),
    API_NOT_IN_WHITELIST(302, "IP訪問限制"),

    ;

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

