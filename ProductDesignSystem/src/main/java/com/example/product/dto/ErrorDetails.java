package com.example.product.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class ErrorDetails {
	private Date date;
	private String errorMessage;
	private String description;
	

}
