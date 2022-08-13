package com.example.product.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.product.dto.ErrorDetails;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorDetails > handleProductNotFoundException(ProductNotFoundException ex, WebRequest webRequest)
	{
		ErrorDetails error = new ErrorDetails (new Date(), ex.getMessage(),webRequest.getDescription(false));
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ProductApiException.class)
	public ResponseEntity<ErrorDetails > handleProductApiException(ProductApiException ex, WebRequest webRequest)
	{
		ErrorDetails error = new ErrorDetails (new Date(), ex.getMessage(),webRequest.getDescription(false));
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalApiException(Exception exception, WebRequest webRequest){
		ErrorDetails error = new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	Map<String,String> errors = new HashMap<String,String>();
	ex.getBindingResult().getAllErrors().forEach(error -> {
		
		String fieldName = ((FieldError) error).getField();
		String message = error.getDefaultMessage();
		errors.put(fieldName, message);
	});
	
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
	
	

}
