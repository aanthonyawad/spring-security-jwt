package com.awad.anthony.springsecurityjwt.security.requests;

import com.fasterxml.jackson.annotation.JsonAlias;

public class AuthenticationRequest {


	@JsonAlias(value = "username")
	private String username;
	
	@JsonAlias(value = "password")
	private String password;
	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public AuthenticationRequest() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
