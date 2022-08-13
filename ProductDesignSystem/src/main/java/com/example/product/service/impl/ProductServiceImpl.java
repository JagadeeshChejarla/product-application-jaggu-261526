package com.example.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.product.dto.ProductResponse;
import com.example.product.dto.ProductsDto;
import com.example.product.entity.Product;
import com.example.product.exceptions.ProductNotFoundException;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductsDto createProduct(ProductsDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
     Product savedProduct = productRepository.save(product);
		return modelMapper.map(savedProduct, ProductsDto.class);
	}

	@Override
	public ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Product> prod = productRepository.findAll(pageable);
		ProductResponse response = new ProductResponse();
		List<ProductsDto> products = prod.stream().map(product->modelMapper.map(product, ProductsDto.class)).collect(Collectors.toList());
		response.setContent(products);
		response = modelMapper.map(prod, ProductResponse.class);
		return response;
	}

	@Override
	public ProductsDto getProductById(long id) {
	   Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product","id",id));
		return modelMapper.map(product, ProductsDto.class);
	}

	@Override
	public ProductsDto updateProductById(long id,ProductsDto productsDto) {
		Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product","id",id));
		product.setBrand(productsDto.getBrand());
		product.setName(productsDto.getName());
		product.setPrice(productsDto.getPrice());
		Product updatedProduct = productRepository.save(product);
		ProductsDto response = modelMapper.map(updatedProduct, ProductsDto.class); 
		
		return response;
	}

	@Override
	public void deleteProduct(long id) {
		Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product","id",id));
		productRepository.delete(product);
		
	}

	@Override
	public List<Product> searchProducts(String query) {
		
		
		return productRepository.searchProducts(query);
	}

	
	
}
