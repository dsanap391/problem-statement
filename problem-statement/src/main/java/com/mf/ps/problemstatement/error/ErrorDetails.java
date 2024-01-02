package com.mf.ps.problemstatement.error;

public class ErrorDetails {
	
	private final String code;
    private final String[] messages;

    public ErrorDetails(String code, String... messages) {
        this.code = code;
        this.messages = messages;
    }

    public String getCode() {
        return code;
    }

    public String[] getMessages() {
        return messages;
    }

}
