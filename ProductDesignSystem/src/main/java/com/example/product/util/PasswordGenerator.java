package com.example.product.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		PasswordEncoder password = new BCryptPasswordEncoder();
		System.out.println(password.encode("Admin@2615"));

	}

}
