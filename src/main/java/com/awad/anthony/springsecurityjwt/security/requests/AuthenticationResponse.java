package com.awad.anthony.springsecurityjwt.security.requests;

import com.fasterxml.jackson.annotation.JsonAlias;

public class AuthenticationResponse {

	@JsonAlias(value = "jwt")
	private String jwt;

	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	

}
