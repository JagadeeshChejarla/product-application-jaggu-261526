package com.example.product.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductResponse {
	private List<ProductsDto> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int  totalPages;
	private boolean islast;

}
