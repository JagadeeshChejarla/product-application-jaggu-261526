package com.example.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value="select * from myproducts p where p.name LIKE CONCAT(CONCAT('%',:query), '%') ",nativeQuery = true)
	List<Product> searchProducts(@Param(value = "query") String query);
}
