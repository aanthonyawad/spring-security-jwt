package com.awad.anthony.springsecurityjwt.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.awad.anthony.springsecurityjwt.security.models.CustomUserDetails;
import com.awad.anthony.springsecurityjwt.security.models.Users;
import com.awad.anthony.springsecurityjwt.security.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Users> userOptional = userRepository.findByEmail(email);
		
		userOptional.orElseThrow(()-> new UsernameNotFoundException("Email not Found"));
		
		return userOptional.map(CustomUserDetails::new).get();
	}

}
