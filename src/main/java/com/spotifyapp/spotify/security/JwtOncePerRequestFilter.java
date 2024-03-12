package com.spotifyapp.spotify.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Create a new class JwtOncePerRequestFilter which extends OncePerRequestFilter
//@Component
public class JwtOncePerRequestFilter extends OncePerRequestFilter {

	//Import the values from the application.properties file
	@Value("${app.jwt.secret}")
	private String secret;
	
	// Override the doFilterInternal method
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		 String requestURI = request.getRequestURI();
	        if (requestURI.equals("/api/v1/register") || requestURI.equals("/api/v1/login")) {
	            filterChain.doFilter(request, response);
	            return;
	        }
	        String token = request.getHeader("Authorization");
	        if (token != null && token.startsWith("Bearer ")) {
	            token = token.substring(7);
	            try {
	            	String username= Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	             if (username != null) { 
	              filterChain.doFilter(request, response);
	             } else {
	                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid Token...........");
	             }
	            } catch (Exception e) {
	                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid Token");
	            }
	        } else {
	            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token is missing");
	        }
	    }
}
