package com.example.product.exceptions;

import org.springframework.http.HttpStatus;

public class ProductApiException extends RuntimeException {
	private HttpStatus httpStatus;
	private String errorMessage;

	public ProductApiException(HttpStatus httpStatus, String errorMessage) {
		super();
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
	}

	public ProductApiException(HttpStatus httpStatus, String errorMessage,String errorMessage1) {
		super(errorMessage);
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage1;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	
	
	

}
