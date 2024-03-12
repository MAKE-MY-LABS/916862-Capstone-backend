package com.spotifyapp.spotify.util;

//Create a component class to generate the JWT token
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	
	@Value("${app.jwt.secret}")
	private String secret;

	// Create a method to generate the JWT token
	/**
	 * This method is used to generate the JWT token
	 * 
	 * @return String
	 */
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}

	// Create a method to create the token
	/**
	 * This method is used to create the token
	 * 
	 * @return String
	 */
	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	// Create a method to validate the token
	/**
	 * This method is used to validate the token
	 * 
	 * @return boolean
	 */
	public boolean validateToken(String token, String username) {
		String tokenUsername = getUsernameFromToken(token);
		return (tokenUsername.equals(username) && !isTokenExpired(token));
	}

	// Create a method to get the username from the token
	/**
	 * This method is used to get the username from the token
	 * 
	 * @return String
	 */
	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	// Create a method to check the token is expired or not
	/**
	 * This method is used to check the token is expired or not
	 * 
	 * @return boolean
	 */
	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}
}