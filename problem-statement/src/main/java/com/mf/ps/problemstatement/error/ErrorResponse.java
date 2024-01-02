package com.mf.ps.problemstatement.error;

public class ErrorResponse {

	private final String status;
    private final ErrorDetails errorResponse;

    public ErrorResponse(String status, ErrorDetails errorResponse) {
        this.status = status;
        this.errorResponse = errorResponse;
    }

    public String getStatus() {
        return status;
    }

    public ErrorDetails getErrorResponse() {
        return errorResponse;
    }
}
