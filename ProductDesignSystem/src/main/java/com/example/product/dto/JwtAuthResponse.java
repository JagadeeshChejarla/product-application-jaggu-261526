package com.example.product.dto;

public class JwtAuthResponse {
	
	private String accessToken;

	private String type="Bearer";
	public JwtAuthResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
