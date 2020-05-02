/**
w * This class is responsible by generate a new token with an secret and an expiration time, defined in the properties file.
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
	private Long expiration;
	
	/**
	 * Generate a new token from the username previous authenticated.
	 * @param username String
	 * @return String
	 */
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(getExpiration())
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}

	/**
	 * Verify if the token parameter is valid.  
	 * @param token String
	 * @return boolean
	 */
	public boolean tokenIsValid(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get a username from token parameter.
	 * @param token String
	 * @return String
	 */
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	/**
	 * Get Claims from token parameter.
	 * @param token String
	 * @return Claims
	 */
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			return null;
		}
	}
	 
	/**
	 * Get the expiraton Date from system in according to now.
	 * @return Date
	 */
	public Date getExpiration() {
		long now = new Date().getTime();
		this.expiration = this.expiration + now;
   
        return new Date(expiration);
	}

	
	
}
