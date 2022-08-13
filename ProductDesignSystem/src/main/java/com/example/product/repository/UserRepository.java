package com.example.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product.entity.ProductUser;

public interface UserRepository extends JpaRepository<ProductUser,Long>{
	Optional<ProductUser> findByEmail(String email);
	Optional<ProductUser> findByUsernameOrEmail(String username,String email);
	Optional<ProductUser> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
	

}
