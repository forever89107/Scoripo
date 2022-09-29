package com.my.core.exception;

import com.my.core.error.ErrorCode;

import java.util.List;

@SuppressWarnings("unused")
public class ServerRunTimeException extends RuntimeException {

  private ErrorCode errorCode;

  private List<String> args;

  /**
   * constructor.
   * @param errorCode 狀態碼.
   * @param args 錯誤參數.
   */
  public ServerRunTimeException(ErrorCode errorCode, List<String> args) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.args = args;
  }
  /**
   * constructor.
   * @param errorCode 狀態碼.
   */
  public ServerRunTimeException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }


  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public List<String> getArgs() {
    return args;
  }

  public void setArgs(List<String> args) {
    this.args = args;
  }

  private static final long serialVersionUID = 1586629652868323070L;

  public ServerRunTimeException() {
    super();
  }

  public ServerRunTimeException(ErrorCode errorCode, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(errorCode.getMessage(), cause, enableSuppression, writableStackTrace);
    this.errorCode = errorCode;
  }

  public ServerRunTimeException(ErrorCode errorCode, Throwable cause) {
    super(errorCode.getMessage(), cause);
    this.errorCode = errorCode;
  }


}
