package com.my.core.interceptor;

import com.my.core.error.ErrorCode;
import com.my.core.exception.ServerRunTimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.my.core.result.Result;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ControllerExceptionHandler<T>  {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);


    /**
     * exception. 統一異常處理 .
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<T> exception(Exception exception) {
        log.error("發生錯誤", exception);
        Result<T> result = new Result<>();
        result.changeErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
        return result;
    }

    /**
     * 沒有權限 .
     * @param accessDeniedException .
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result<T> exception(AccessDeniedException accessDeniedException) {
        log.error("無訪問權限", accessDeniedException);
        Result<T> result = new Result<>();
        result.changeErrorCode(ErrorCode.UNAUTHORIZED);
        return result;
    }

    /**
     * 自訂義錯誤.
     *
     * @param exception .
     */
    @ExceptionHandler(ServerRunTimeException.class)
    @ResponseBody
    public Result<T> serverException(ServerRunTimeException exception) {
        Result<T> result = new Result<>();
        if (log.isDebugEnabled()) {
            log.debug("自訂義錯誤[{}] {}", exception.getErrorCode().getCode(),
                    exception.getErrorCode().getMessage(), exception);
        }
        result.changeErrorCode(exception.getErrorCode());
        return result;
    }

    /**
     * resteasy 校驗錯誤.
     *
     * @param exception 錯誤.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<T> ideaException(MethodArgumentNotValidException exception) {
        Result<T> result = new Result<>();
        if (log.isDebugEnabled()) {
            log.debug("參數錯誤", exception);
        }
        result.changeErrorCode(ErrorCode.BAD_REQUEST);
        return result;
    }

}
