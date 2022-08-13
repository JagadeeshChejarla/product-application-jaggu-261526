package com.springboot.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CommentDto {
	private long id;
	@NotEmpty(message="name must be filled")
	private String name;
	@NotEmpty(message="email must be filled")
	@Email(message = "enter valid email")
	private String email;
	@NotEmpty(message = "body must be filled")
	private String body;
	
	public CommentDto() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public CommentDto(long id, String name, String email, String body) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.body = body;
	}

	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", name=" + name + ", email=" + email + ", body=" + body + "]";
	}
	

}
