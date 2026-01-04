package com.udemy.spring.application.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.udemy.spring.application.dtos.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtServiceImpl implements JwtService {
	@Value("${jwt.expiration}")
	private String jwtExpiration;
	
	@Value("${jwt.key}")
	private String jwtKey;
	
	@Override
	public String buildToken(UserDTO userDto) {
		long expiration = Long.valueOf(jwtExpiration); // in milliseconds
		LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(expiration); // calculate expiration time
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant(); // convert to instant
		Date date = Date.from(instant); // convert to date
		String expirationTime = localDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")); // format expiration time
		
		String token = Jwts.builder()
				.setExpiration(date)
				.setSubject(userDto.getUsername())
				.claim("userId", userDto.getId())
				.claim("expirationTime", expirationTime)
				.signWith(SignatureAlgorithm.HS512, jwtKey) // sign the token with the secret key (private key)
				.compact();
		
		return "Bearer " + token;
	}

	@Override
	public Claims getClaims(String token) throws ExpiredJwtException {
		return Jwts.parser()
				.setSigningKey(jwtKey)
				.parseClaimsJws(token)
				.getBody();
	}

	@Override
	public boolean isValidToken(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUsername(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
