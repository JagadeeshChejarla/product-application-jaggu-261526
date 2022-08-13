package com.example.product.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.product.exceptions.ProductApiException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;
    
    public String generateToken(Authentication authentication) {
    	String username = authentication.getName();
    	Date currentDate = new Date();
    	Date expiryDate = new Date(currentDate.getTime()+jwtExpirationInMs);
    	
    	String token = Jwts.builder()
    			      .setSubject(username)
    			      .setIssuedAt(currentDate)
    			      .setExpiration(expiryDate)
    			      .signWith(SignatureAlgorithm.HS512, jwtSecret)
    			      .compact();
    	
    	return token;
    }
    
    public String getUsernameFromJwt(String token) {
    	Claims claim = Jwts.parser()
    			      .setSigningKey(jwtSecret)
    			      .parseClaimsJws(token)
    			      .getBody();
    	
    	return claim.getSubject();
    	
    }
    
    public boolean validateToken(String token) {
    	try {
        	Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        	return true;
        	}
        	catch (SignatureException ex){
                throw new ProductApiException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
            } catch (MalformedJwtException ex) {
                throw new ProductApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
            } catch (ExpiredJwtException ex) {
                throw new ProductApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
            } catch (UnsupportedJwtException ex) {
                throw new ProductApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
            } catch (IllegalArgumentException ex) {
                throw new ProductApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
            }
    }

}