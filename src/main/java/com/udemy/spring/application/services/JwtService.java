package com.udemy.spring.application.services;

import com.udemy.spring.application.dtos.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface JwtService {
	// Method to build a JWT token for a given user
	String buildToken(UserDTO userDto);
	
	// Method to extract claims from a JWT token
	Claims getClaims(String token) throws ExpiredJwtException;
	
	// Method to check if a token is expired
	boolean isValidToken(String token);
	
	// Method to extract login/username from a JWT token
	String getUsername(String token);
}
