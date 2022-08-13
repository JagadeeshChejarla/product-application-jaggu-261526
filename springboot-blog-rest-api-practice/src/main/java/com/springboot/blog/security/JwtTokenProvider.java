package com.springboot.blog.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
	@Value("${app.jwt-secret}")
	private String jwtSecretKey;
	@Value("${app.jwt-expiration-milliseconds}")
	private String jwtExpirationMilliseconds;

}
