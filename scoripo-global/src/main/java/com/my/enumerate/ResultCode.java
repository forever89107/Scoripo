package com.my.enumerate;

public enum ResultCode {

    SUCCESS(200, "SUCCESS"),
    //Bad Request
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

    KICKED_OUT(505, "用戶被踢出"),
    USER_PASSWORD_ERROR(102, "帳號密碼錯誤"),
    USER_LOCKING(103, "帳號已被鎖定"),
    LOG_FAIL(104, "用戶不存在或密碼錯誤或帳號已鎖定"),
    MOBILE_VALIDATE_ERROR(200, "驗證碼校驗失敗"),
    VALIDATE_CODE_ERROR(201, "驗證碼校驗失敗"),
    PASSWORD_WRONG(202, "密碼不符合規範"),
    AREA_NO_SUPPORT(299, "區號暫時不支持"),

    INVALID_PARAM(10000, "參數錯誤"),

    NOT_LOGGED_IN(20002, "未登錄"),
    MAX_LIMIT_PASSWORD_ERROR(20009, "用戶名或者密碼錯誤"),
    USERNAME_OR_PWD_WRONG(20004, "用戶名或者密碼錯誤"),
    USER_NAME_ERROR(20005, "用戶名不符合要求"),
    PASSWOWRD_WRONG(20006, "密碼不符合規範"),
    REGISTER_USER_NAME_ERROR(20011, "注冊用戶名不符合規範[6-16個字母及數字組合]"),
    AMOUNT_ERROR(20007, "金額不符合規範"),
    TRANSFER_UNAVAILABLE(20010, "您的賬號無平台轉賬權限"),
    TRANSFER_AMOUNT_LOCK(20014, "資金已鎖定，請先資金解鎖"),
    TRANSFER_ERROR(20015, "轉賬失敗"),
    BIND_OTHER_USER_BANK_ERROR(20018, "該銀行卡已綁定其它用戶"),
    UNSUPPORTED_BANK_ERROR(20019, "暫不支持，建議換一張卡重試"),

    CARD_VALIDATE_ERROR(20020, "銀行卡校驗失敗，請稍後重試"),
    CARD_VALIDATEEXCEED_ERROR(20021, "校驗次數超限，請您明天再試"),
    CARD_VALIDATE_CONTACT_ERROR(20023, "請聯系客服人員"),
    IMG_CODE_ERROR(200025, "驗證錯誤"),
    NAME_VALIDATE_ERROR(20028, "姓名和卡號不匹配或卡有異常"),
    CARD_EXIST_ERROR(20029, "銀行卡已綁定"),
    PASSWOWRD_SAME_WRONG(20030, "新密碼不能和舊密碼一致"),
    MOBILE_ERROR(20035, "手機號格式錯誤"),
    USER_NAME_EXIST(20001, "用戶名已存在"),
    RECHARGE_AMOUNT_ERROR(20036, "充值金額異常"),
    WITHDRAWAL_CLOSED(20042, "平台提現已關閉"),
    PAYOUT_PWD_WRONG(20045, "代充密碼錯誤"),
    BANKCARD_CARD_NO_FORMAT_ERROR(20046, "請輸入正確格式的銀行卡賬號"),
    BANKCARD_NAME_FORMAT_ERROR(20047, "銀行卡姓名格式不正確"),
    PASSWOWRD_NOT_SAME_WRONG(20048, "輸入的新密碼與確認密碼不一致"),
    SMS_CODE_ERROR(200049, "驗證碼信息異常"),
    BANKCARD_NOT_BINDING(20050, "銀行卡尚未綁訂"),
    PASSWORD_HAS_TO_MODIFY(20051, "您是新用戶，請修改初始密碼"),
    BALANCE_HAS_TO_VIEW(20052, "賬戶有待審核資金流水調整"),
    INSUFFICIENT_BET_AMOUNT(20053, "賬戶有待審核資金流水調整"),

    BIND_YOUR_PHONE_NUMBER_FIRST(200020, "請先綁定手機號"),
    MOBILE_NOT_EXIST(200021, "手機號碼不存在"),
    USER_NAME_NOT_EXIST(20003, "用戶名不存在"),
    MOBILE_EXIST(200022, "手機號碼已經存在"),
    EMAIL_EXIST(20008, "郵箱已存在"),
    RECHARGE_CHANNEL_CLOSED(200024, "充值渠道已關閉"),
    NEED_ACTION_VALIDATE(200028, "需要行為驗證"),
    TRANSFER_GAME_NOT_ENOUGH_LEFT_MONEY(200029, "遊戲可下分余額不足"),
    BIND_PIX_ADDRESS_LIMIT(200030, "PIX綁定地址已達最大數量"),
    DELETE_PIX_ADDRESS_PERMISSION_DENIED(200031, "您的帳號沒有權限刪除PIX地址"),
    DELETE_PIX_ADDRESS_FAIL(200032, "剩下一個PIX地址無法刪除"),
    PARAM_ERROR_EMAIL(200033, "Email格式錯誤"),
    PARAM_ERROR_CPF_AND_PHONE_LENGTH(200034, "PIX地址請輸入11位數字"),
    PARAM_ERROR_CNPJ_LENGTH(200035, "PIX地址請輸入14位數字"),

    BOUND_PIX_ADDRESS(200036, "PIX地址已被綁定"),
    RECHARGE_BEFORE_BIND_PIX_ADDRESS(200037, "充值前，請先綁定您的PIX地址"),

    PLEASE_INPUT_YOUR_NAME(200038, "請輸入您的姓名"),
    PARAM_ERROR_PIX_TYPE_OR_PIX_ADDRESS_IS_BLANK(200039, "PIX類型PIX或地址為空"),
    PARAM_ERROR_SUPPORT_PIX_TYPE(200040, "尚未支援此PIX類型"),
    PARAM_ERROR_PIX_ADDRESS_MUST_BE_NUMBER(200041, "PIX地址錯誤，必須為數字"),

    BALANCE_LACKING(300000, "余額不足"),

    WITHDRAW_MULTIPLE_ERROR(400001, "提現流水倍數異常"),
    RECHARGE_SUBMIT_ERROR(400002, "充值失敗,請聯系財務客服"),
    USDT_ERROR_CODE(400003, "USDT異常編碼"),
    WITHDRAW_SUBMIT_ERROR(400004, "提現異常信息"),

    LOGIN_DEVICE_ABNORMAL(500001, "非授信設備登錄需要二次驗證"),

    QUERY_NO_PERMISSIONS(40501, "頁面查詢條件無權限"),

    TOOLS_INTERFACE_DISABLE(900000, "接口已停用"),
    TOOLS_SMS_CODE_SEND_ERROR(900001, "短信驗證碼發送失敗"),
    TOOLS_SMS_CODE_VALID_ERROR(900002, "短信驗證碼校驗失敗"),
    TOOLS_SMS_CODE_WRONG(900003, "驗證碼校驗失敗"),
    TOOLS_GET_USER_INFO_ERROR(900004, "獲取用戶信息錯誤"),
    TOOLS_GET_USER_SECURITY_ERROR(900005, "獲取用戶提現保護配置錯誤"),
    TOOLS_DATA_NOT_SEARCH_ERROR(900006, "數據不存在，無法查詢"),
    TOOLS_DATA_NOT_EXPORT_ERROR(900007, "數據不存在，無法導出"),



    UNKNOWN_MISTAKE(-1, "未知錯誤"),
    DATA_NOSEARCH(-2, "未查詢到相應條件的數據"),
    SMS_FREQUENCY_VALIDATE_FAIL(3000, "發送短信驗證碼頻率偏高，請稍後再試"),
    SMS_API_NOCONFIG(3001, "短信發送API未配置"),
    EMAIL_TEMPLATE_NOCONFIG(3002, "郵件驗證碼模版未配置"),
    SMS_CODE_EXPIRED(3003, "短信驗證碼已過期，請重新獲取"),
    ;

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ResultCode gainResultCode(int code) {
        for (ResultCode value : ResultCode.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        return ResultCode.FAIL_DEFAULT;
    }

}

