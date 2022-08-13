package com.example.product.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.dto.JwtAuthResponse;
import com.example.product.dto.LoginDto;
import com.example.product.dto.SignupDto;
import com.example.product.entity.ProductUser;
import com.example.product.entity.RoleName;
import com.example.product.repository.RoleRepository;
import com.example.product.repository.UserRepository;
import com.example.product.security.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Authorization APIs for signup and signin ")
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private UserRepository user;
	@Autowired
	private RoleRepository roles;
	@Autowired
	private PasswordEncoder encoder;
	@ApiOperation(value="Sign in user to acces the application")
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LoginDto loginDto){
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		String token = tokenProvider.generateToken(auth);
		return ResponseEntity.ok(new JwtAuthResponse(token));
		
		
	}
	@ApiOperation(value="Registering as a user for application")
	@PostMapping("/signup")
	public ResponseEntity<String> createUser(@RequestBody SignupDto signupDto){
		
		if(user.existsByUsername(signupDto.getUsername())) {
			return new ResponseEntity ("User already exists", HttpStatus.BAD_REQUEST);
		}

		if(user.existsByEmail(signupDto.getEmail())) {
			return new ResponseEntity ("User already exists", HttpStatus.BAD_REQUEST);
		}
		
	ProductUser pUser = new ProductUser();
	pUser.setEmail(signupDto.getEmail());
	pUser.setName(signupDto.getName());
	pUser.setPassword(encoder.encode(signupDto.getPassword()));
	pUser.setUsername(signupDto.getUsername());
	RoleName role = roles.findByName("ROLE_USER").get();
	pUser.setRoles(Collections.singleton(role));
	
	    user.save(pUser);
		return new ResponseEntity<>("User registed successfully",HttpStatus.OK);
	}
	
	

}
