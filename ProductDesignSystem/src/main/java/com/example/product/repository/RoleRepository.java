package com.example.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product.entity.RoleName;

public interface RoleRepository extends JpaRepository<RoleName, Long> {
	Optional<RoleName> findByName(String name);

}
