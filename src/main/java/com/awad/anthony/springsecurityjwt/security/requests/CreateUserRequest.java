package com.awad.anthony.springsecurityjwt.security.requests;

import com.fasterxml.jackson.annotation.JsonAlias;

public class CreateUserRequest {
	@JsonAlias(value = "username")
	private String username;

	@JsonAlias(value = "first_name")
	private String firtName;

	@JsonAlias(value = "last_name")
	private String lastName;

	@JsonAlias(value = "password")
	private String password;
	
	
	public CreateUserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CreateUserRequest(String username, String firtName, String lastName, String password) {
		super();
		this.username = username;
		this.firtName = firtName;
		this.lastName = lastName;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirtName() {
		return firtName;
	}


	public void setFirtName(String firtName) {
		this.firtName = firtName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
