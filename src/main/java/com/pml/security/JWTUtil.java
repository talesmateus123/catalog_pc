/**
 * This class is responsible by generate a new token with an secret and an expiration time, defined in the properties file.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private String expiration;
	
	@SuppressWarnings("deprecation")
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}

	public boolean tokenIsValid(String token) {
		Claims claims = getClaims(token);
		if(claims != null){
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if(username != null && expirationDate != null && now.before(expirationDate))
				return true;
		}
		return false;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch(Exception e) {
			return null;
		}
	}

	public String getUserName(String token) {
		Claims claims = getClaims(token);
		if(claims != null) 
			return claims.getSubject();
		return null;
	}
	
	
}
