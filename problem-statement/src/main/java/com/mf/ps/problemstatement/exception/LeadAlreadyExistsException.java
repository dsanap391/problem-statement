package com.mf.ps.problemstatement.exception;

public class LeadAlreadyExistsException extends RuntimeException{
	private String errorCode;

	public LeadAlreadyExistsException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
