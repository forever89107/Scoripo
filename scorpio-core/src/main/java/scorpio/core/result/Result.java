package scorpio.core.result;


import lombok.Getter;
import lombok.Setter;
import scorpio.core.error.ErrorCode;

import java.io.Serializable;


@Getter
@Setter
@SuppressWarnings("unused")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 2580757218023862568L;

    private int code;
    private String msg;
    private T data;

    /**
     * constructor
     */
    public Result() {
        this.code = ErrorCode.OK.getCode();
        this.msg = ErrorCode.OK.getMessage();
    }

    /**
     * constructor
     */
    public Result(T data) {
        this.code = ErrorCode.OK.getCode();
        this.msg = ErrorCode.OK.getMessage();
        this.data = data;
    }

    /**
     * constructor
     */
    public Result(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }

    /**
     * constructor
     */
    public Result(ErrorCode errorCode, String msg) {
        this.code = errorCode.getCode();
        this.msg = msg;
    }

    /**
     * constructor
     */
    public Result(ErrorCode errorCode, String msg, T data) {
        this.code = errorCode.getCode();
        this.msg = msg;
        this.data = data;

    }

    /**
     * constructor
     */
    public Result(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
        this.data = data;
    }


    /**
     * ErrorCode transfer.
     */
    public void changeErrorCode(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }


    /**
     * success
     */
    public void successMg() {
        changeErrorCode(ErrorCode.OK);
    }


    /**
     * InternalServerError.
     */
    public void internalServerError() {
        changeErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
    }


    /**
     * UNAUTHORIZED.
     */
    public void noPermissionMg() {
        changeErrorCode(ErrorCode.UNAUTHORIZED);
    }


    /**
     * Illegal_Parameter.
     */
    public void illegal_ParameterMg() {
        changeErrorCode(ErrorCode.BAD_REQUEST);
    }


    /**
     * Not found.
     */
    public void noFindMg() {
        changeErrorCode(ErrorCode.NOT_FOUND);
    }
}
