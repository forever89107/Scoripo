package com.my.core.error;

/**
 * 狀態碼，分類
 */
@SuppressWarnings("unused")
public enum ErrorCode {
   /** OK : 操作成功.*/
   OK(200, "success"),

   /** BAD_REQUEST : 參數出錯.*/
   BAD_REQUEST(400, "參數出錯"),

   /** AUTH_FAIL : 未經授權的操作.*/
   UNAUTHORIZED(401, "未經授權的操作"),

   /** 拒絕訪問.*/
   FORBIDDEN(403, "拒絕訪問"),

   /** 404.*/
   NOT_FOUND(404, "404"),

   /** 發送太多的請求.*/
   TOO_MANY_REQUESTS(429, "發送太多的請求"),

   /** EXCEPTION : 服務錯誤.*/
   INTERNAL_SERVER_ERROR(500, "服務錯誤"),

   /** SERVICE_UNAVAILABLE.*/
   SERVICE_UNAVAILABLE(503, "對不起，服務負載過重，請稍後操作！"),

   // -------用户（20000） start---------//
   ACCOUNT_NOT_FOUND(20000,"查無此用戶"),
   PWD_ERROR(20001,"密碼錯誤"),
   ILLEGAL_ACCOUNT(20002,"用戶為非法狀態"),
   // -------用户（20099） end---------//

   ;
   private final int code;
   private final String message;

   ErrorCode(int code, String message) {
      this.code = code;
      this.message = message;
   }

   public String getMessage() {
      return message;
   }

   public int getCode() {
      return code;
   }

   @Override
   public String toString() {
      return code + ": " + message;
   }
}
