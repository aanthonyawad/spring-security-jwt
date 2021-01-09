package com.awad.anthony.springsecurityjwt.security.requests;

import com.fasterxml.jackson.annotation.JsonAlias;

public class IsAuthenticatedResponse {
	
	@JsonAlias(value = "authenticated")
	private boolean authenticated;

	public IsAuthenticatedResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IsAuthenticatedResponse(boolean authenticated) {
		super();
		this.authenticated = authenticated;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	
}
