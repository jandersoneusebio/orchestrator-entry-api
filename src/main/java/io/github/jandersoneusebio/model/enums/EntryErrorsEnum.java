package io.github.jandersoneusebio.model.enums;

public enum EntryErrorsEnum {
	
	ENTRY_ALREADY_EXISTS(101, "Given entry already exists and is active."),
	INVALID_ENTRY_TYPE(102, "Given entry type doesn't match any valid type."),
	INVALID_ENTRY_TYPE_WITH_PARAM(103, "Given entry type (%s) doesn't match any valid type.");
	
	private Integer errorCode;
	
	private String msg;
	
	EntryErrorsEnum(Integer errorCode, String msg) {
		this.errorCode = errorCode;
		this.msg = msg;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getMsg() {
		return msg;
	}
	
}
