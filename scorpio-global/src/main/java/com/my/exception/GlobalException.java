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

	private String[] passValue;

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
		super(localConfigKey.getKey(),cause);
		this.localConfigKey = localConfigKey;
	}

	@Deprecated
	public GlobalException(int code, String message, String extraMessage, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.extraMessage = extraMessage;
	}

	@Deprecated
	public GlobalException(int code, String message, LocalKey localConfigKey) {
		super(message);
		this.code = code;
		this.localConfigKey = localConfigKey;
		this.extraMessage = localConfigKey.getDesc();
	}


	public GlobalException(int code, String message, String extraMessage, LocalKey localConfigKey, Throwable cause) {
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
		this(resultCode.getCode(), resultCode.getMessage(), localConfigKey);
	}

	public GlobalException(ResultCode resultCode, LocalKey localConfigKey, String... passValue) {
		super(resultCode.getMessage());
		this.code = resultCode.getCode();
		this.localConfigKey = localConfigKey;
		this.extraMessage = localConfigKey.getDesc();
		this.passValue = passValue;
	}

	public GlobalException(String extraMessage) {
		this(ResultCode.INTERNAL_SERVER_ERROR, extraMessage);
	}

	public GlobalException(LocalKey localConfigKey) {
		this(ResultCode.INTERNAL_SERVER_ERROR, localConfigKey);
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

	public String[] getPassValue() {
		return passValue;
	}
}
