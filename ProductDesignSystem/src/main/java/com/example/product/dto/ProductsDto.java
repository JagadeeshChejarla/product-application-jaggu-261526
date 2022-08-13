package com.example.product.dto;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {
	
	private long id;
	@NotEmpty(message="Should not be empty")
	private String name;
	@NotEmpty(message="Should not be empty")
	private String brand;
	@NotEmpty(message="Should not be empty")
	private String price;
	
	}
