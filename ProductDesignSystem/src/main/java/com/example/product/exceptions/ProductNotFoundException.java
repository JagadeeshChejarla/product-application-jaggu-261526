package com.example.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{
	private String resourceName;
	private String fieldName;
	private long fieldValue;
	
	public ProductNotFoundException(String resourceName,String fieldName,long fieldValue) {
		super(String.format("%s is not found with %s : '%s'", resourceName,fieldName,fieldValue));
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.resourceName = resourceName;
		
	
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public long getFieldValue() {
		return fieldValue;
	}


}
