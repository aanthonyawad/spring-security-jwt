package com.awad.anthony.springsecurityjwt.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.awad.anthony.springsecurityjwt.exceptions.JwtErrnException;
import com.awad.anthony.springsecurityjwt.security.models.Users;
import com.awad.anthony.springsecurityjwt.security.repositories.UserRepository;
import com.awad.anthony.springsecurityjwt.security.requests.AuthenticationRequest;
import com.awad.anthony.springsecurityjwt.security.requests.AuthenticationResponse;
import com.awad.anthony.springsecurityjwt.security.requests.CreateUserRequest;
import com.awad.anthony.springsecurityjwt.security.requests.IsAuthenticatedResponse;
import com.awad.anthony.springsecurityjwt.security.util.JwtUtil;
import com.awad.anthony.springsecurityjwt.services.MyUserDetailsService;

@RestController
public class HelloResource {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtUtil jwtUtil;
	
	
	
	
	@PostMapping(value="/createuser")
	public ResponseEntity<?> signUp(@RequestBody CreateUserRequest createUserRequest) throws Exception{

		Users userEntity = new Users(createUserRequest);
		UserDetails userDetails = null;
		
		try {
			userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());
			throw new Exception("username Found Exception");
		}catch(UsernameNotFoundException e) {
			userEntity = userRepository.save(userEntity);
		}
		
		userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
	
	
	
	@PostMapping(value="/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
					
		}catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password",e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
	
	
	@GetMapping(value="/isauthenticated")
	public ResponseEntity<?>isAuthenticated(){
		return ResponseEntity.ok(new IsAuthenticatedResponse(true));
	}
}

