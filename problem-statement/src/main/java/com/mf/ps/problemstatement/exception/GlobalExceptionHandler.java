package com.mf.ps.problemstatement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mf.ps.problemstatement.error.ErrorDetails;
import com.mf.ps.problemstatement.error.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(LeadAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleLEadAreadyExistsException(LeadAlreadyExistsException ex){
		 ErrorResponse errorResponse = new ErrorResponse("error", new ErrorDetails(ex.getErrorCode(), ex.getMessage()));
	       return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
}
