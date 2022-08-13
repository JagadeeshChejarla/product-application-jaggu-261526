package com.example.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.dto.ProductResponse;
import com.example.product.dto.ProductsDto;
import com.example.product.entity.Product;
import com.example.product.service.ProductService;
import com.example.product.util.AppConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="Product API for crud operations")
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@ApiOperation(value="Creating Product")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<ProductsDto> createProduct( @Valid @RequestBody ProductsDto productDto){
		ProductsDto response = productService.createProduct(productDto);
		return new ResponseEntity<>(response,HttpStatus.CREATED);	
	}
	@ApiOperation(value="Retrival of all Product")
	@GetMapping
	public ProductResponse getAllProducts(
			@RequestParam(value="pageNo", defaultValue = AppConstants.default_pageNumber,required = false) int pageNo,
			@RequestParam(value="pageSize", defaultValue = AppConstants.default_pageSize,required = false) int pageSize,
			@RequestParam(value="sortBy", defaultValue = AppConstants.default_sortBy,required = false) String sortBy,
			@RequestParam(value="sortDir", defaultValue = AppConstants.default_sortDir,required = false) String sortDir
			
			) {
		
		return productService.getAllProducts(pageNo, pageSize, sortBy, sortDir) ;
		
	}
	@ApiOperation(value="Retrieval of Product by id")
	@GetMapping("/{id}")
	public ResponseEntity<ProductsDto> getProductById(@PathVariable long id){
		
		return ResponseEntity.ok(productService.getProductById(id));
		
	}
	@ApiOperation(value="Update the  Product")
	@PreAuthorize("hasRole('Admin')")
	@PutMapping("/{id}")
	public ResponseEntity<ProductsDto> updateProduct( @PathVariable long id, @Valid @RequestBody ProductsDto productDto){
		ProductsDto response = productService.updateProductById(id, productDto);
		return new ResponseEntity<>(response,HttpStatus.OK);	
	}
	
	@ApiOperation(value="Delete the  Product")
	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable long id){
		
		return ResponseEntity.ok("Product deleted successfully");
		
	}
	@ApiOperation(value="Searching a Product by name")
	@GetMapping("/search/{query}")
	public ResponseEntity<List<Product>> searchProducts(@PathVariable String query){
		return ResponseEntity.ok(productService.searchProducts(query));
	}


	

}
