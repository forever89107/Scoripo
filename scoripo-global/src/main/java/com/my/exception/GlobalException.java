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

	@Deprecated
	public GlobalException(String message, Throwable cause) {
		super(message, cause);
	}

	public GlobalException(LocalKey localConfigKey, Throwable cause) {
		this.localConfigKey = localConfigKey;
	}

	@Deprecated
	public GlobalException(int code, String message, String extraMessage, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.extraMessage = extraMessage;
	}

	@Deprecated
	public GlobalException(int code, String message, LocalKey localConfigKey, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.localConfigKey = localConfigKey;
	}


	public GlobalException(int code, String message, String extraMessage,LocalKey localConfigKey, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.extraMessage = extraMessage;
		this.localConfigKey = localConfigKey;
	}

	@Deprecated
	public GlobalException(ResultCode resultCode, String extraMessage) {
		this(resultCode.getCode(), resultCode.getMessage(), extraMessage, null);
	}

	public GlobalException(ResultCode resultCode, LocalKey localConfigKey) {
		this(resultCode.getCode(), resultCode.getMessage(), localConfigKey, null);
	}

	@Deprecated
	public GlobalException(String extraMessage) {
		this(ResultCode.SERVER_ERROR, extraMessage);
	}

	public GlobalException(LocalKey localConfigKey) {
		this(ResultCode.SERVER_ERROR, localConfigKey);
	}

	public int getCode() {
		return code;
	}

	public String getExtraMessage() {
		return extraMessage;
	}

	public LocalKey getLocalConfigKey() {
		return localConfigKey;
	}

}
