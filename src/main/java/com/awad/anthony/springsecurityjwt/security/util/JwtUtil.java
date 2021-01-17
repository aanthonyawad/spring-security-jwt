package com.awad.anthony.springsecurityjwt.security.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.awad.anthony.springsecurityjwt.exceptions.JwtErrnException;
import com.awad.anthony.springsecurityjwt.filters.JwtRequestFilter;
import com.awad.anthony.springsecurityjwt.security.requests.IsAuthenticatedResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	
	private String SECRET_KEY = "secret";
	
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public <T> T extractClaim(String token,Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
		
	}

	private Claims extractAllClaims(String token){
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims = new HashMap<>();
		return createToken(claims,userDetails.getUsername());
	}
	private String createToken(Map<String,Object> claims,String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10 )) // 10 hours
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	public boolean validateToken(String token,UserDetails userDetails) {
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

    @ExceptionHandler(value = { JwtErrnException.class })
    public ResponseEntity<Object> handleInvalidInputException(JwtErrnException ex) {
        return new ResponseEntity<Object>(new IsAuthenticatedResponse(false),HttpStatus.BAD_REQUEST);
    }
	
	
}
