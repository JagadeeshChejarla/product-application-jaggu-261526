package com.example.product.service;

import java.util.List;

import com.example.product.dto.ProductResponse;
import com.example.product.dto.ProductsDto;
import com.example.product.entity.Product;

public interface ProductService {
	ProductsDto createProduct(ProductsDto productDto);
	ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);
	ProductsDto getProductById(long id);
	ProductsDto updateProductById(long id,ProductsDto productsDto);
	void deleteProduct(long id);
	List<Product> searchProducts(String query);
	
}
