package com.example.product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.product.security.JwtAutheticationEntryPoint;
import com.example.product.security.JwtAutheticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
	@Autowired
	private JwtAutheticationEntryPoint entryPoint;
	@Autowired
	private  JwtAutheticationFilter authFilter;
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.exceptionHandling()
		.authenticationEntryPoint(entryPoint)
		.and()
		.authorizeRequests()
		.antMatchers("/product/**").permitAll()
		.antMatchers("/auth/**").permitAll()
		.antMatchers("/v2/api-docs/**").permitAll()
		.antMatchers("/swagger-ui/**").permitAll()
		.antMatchers("/swagger-resources/**").permitAll()
		.antMatchers("/swagger-ui.html").permitAll()
		.antMatchers("/webjars/**").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
		http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();	
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration autheticationConfiguration) throws Exception {
		
		return autheticationConfiguration.getAuthenticationManager();
	}
	 
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/v2/api-docs",
	                "/configuration/ui",
	                "/swagger-resources/**",
	                "/configuration/security",
	                "/swagger-ui.html",
	                "/webjars/**");
	    }

}
