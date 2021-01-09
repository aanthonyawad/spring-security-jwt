package com.awad.anthony.springsecurityjwt.security.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.awad.anthony.springsecurityjwt.security.requests.CreateUserRequest;
import com.awad.anthony.springsecurityjwt.security.util.StringUtility;

import antlr.StringUtils;

@Entity
@Table(name="user")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="email")
	private String email;

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="active")
	private int active;
	
	public Users() {
		
	}
	
	public Users(Users user) {
		this.id = user.id;
		this.email = user.email;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.password = user.password;
		this.active = user.active;
	}

	public Users(CreateUserRequest createUserRequest) throws Exception{
		this.active = 1;
		
		if(StringUtility.isEmptyOrNull(createUserRequest.getFirtName())) {
			throw new Exception("first_name not found");
		}
		
		if(StringUtility.isEmptyOrNull(createUserRequest.getLastName())) {
			throw new Exception("last_name not found");
		}
		
		if(StringUtility.isEmptyOrNull(createUserRequest.getUsername())) {
			throw new Exception("username not found");
		}
		
		if(StringUtility.isEmptyOrNull(createUserRequest.getPassword())) {
			throw new Exception("password not found");
		}
		this.email = createUserRequest.getUsername();
		this.firstName = createUserRequest.getFirtName();
		this.lastName = createUserRequest.getLastName();
		this.password = createUserRequest.getPassword();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
}
