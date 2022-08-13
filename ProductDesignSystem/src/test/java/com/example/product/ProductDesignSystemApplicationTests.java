package com.example.product;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.product.dto.ProductsDto;
import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;




@SpringBootTest
class ProductDesignSystemApplicationTests {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ProductService productService;
	
	@Test
	void getAllProduct() {
		List<Product> product = productRepo.findAll();
		int actualSize = product.size();
		int expectedSize = 3;
		assertThat(actualSize<expectedSize).isTrue();
		
	}
	@Test
	@Transactional
	@Rollback(true)
	void addProduct() {
		 ProductsDto product = new ProductsDto();
		 product.setName("Jio");
		 product.setBrand("Mobile");
		 product.setPrice("25000");
		ProductsDto products = productService.createProduct(product);
		product.setId(products.getId());
		assertEquals(product,products);	
	}


}
