package com.my.exception;

import com.my.base.LocalKey;
import com.my.enumerate.ResultCode;
import lombok.Getter;

/**
 * GlobalException
 */
@Getter
public class GlobalException extends Exception {

	private int code;
	private String extraMessage;
	private LocalKey localConfigKey;

	public GlobalException(ResultCode resultCode) {
		super(resultCode.getMessage());
		this.code = resultCode.getCode();
		this.extraMessage = resultCode.getMessage();
	}

	public GlobalException(Throwable cause) {
		super(cause);
	}

	public GlobalException(LocalKey localConfigKey, Throwable cause) {
		this.localConfigKey = localConfigKey;
	}

	public GlobalException(int code, String message, String extraMessage, LocalKey localConfigKey, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.extraMessage = extraMessage;
		this.localConfigKey = localConfigKey;
	}

}
