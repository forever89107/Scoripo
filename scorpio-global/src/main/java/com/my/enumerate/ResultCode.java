package com.my.enumerate;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(200, "SUCCESS"),

    API_LOST_WHITELIST_CONFIG(303, "您的請求,無白名單配置"),
    API_NOT_IN_WHITELIST(302, "IP訪問限制"),

    BAD_REQUEST(400, "請求資料錯誤"),
    UNAUTHORIZED(401, "請求資料錯誤"),
    APP_VERSION_LOW(402, "APP版本過低"),
    FORBIDDEN(403, "訪問受限"),
    TOKEN_UNAVAILABLE(405, "登錄失效"),
    TOO_MANY_REQUESTS(429, "請求頻率太高"),

    INTERNAL_SERVER_ERROR(500, "服務器錯誤"),
    FAIL_DEFAULT(501, "未知錯誤"),
    ILLEGAL_REQUEST(502, "非法請求"),

    PARAM_ERROR(1001, "參數錯誤"),
    REPEAT_LIMIT_ERROR(1003, "5s內重覆提交，請稍後重試"),
    GUESSING_BET_VERIFY_FAIL(1004, "參數檢查不通過"),
    ;

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

