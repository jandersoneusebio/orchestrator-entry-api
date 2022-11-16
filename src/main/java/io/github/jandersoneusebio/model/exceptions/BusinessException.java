package io.github.jandersoneusebio.model.exceptions;

import org.springframework.http.HttpStatus;

import io.github.jandersoneusebio.model.enums.EntryErrorsEnum;
import io.github.jandersoneusebio.model.to.ErrorTO;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 7777298761586607851L;
	
	private ErrorTO error;

	public BusinessException(String msg) {
		super(msg);
		this.error = new ErrorTO(HttpStatus.BAD_REQUEST.ordinal(), msg);
	}
	
	public BusinessException(EntryErrorsEnum entryError) {
		super(entryError.getMsg());
		this.error = new ErrorTO(entryError.getErrorCode(), entryError.getMsg());
	}
	
	public BusinessException(String msg, Integer code) {
		super(msg);
		this.error = new ErrorTO(code, msg);
	}

	public ErrorTO getError() {
		return error;
	}

	public void setError(ErrorTO error) {
		this.error = error;
	}
}
