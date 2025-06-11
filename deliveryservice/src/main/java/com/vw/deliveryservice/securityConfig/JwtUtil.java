package com.vw.deliveryservice.securityConfig;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

	private final SecretKey secretKey;

	 public JwtUtil() {
	        this.secretKey = Jwts.SIG.HS256.key().build(); // Generates a secure 256-bit key
	        System.out.println("Here is your Secret Key : " + secretKey);
	    }
    
    public String generateToken(String username,String role) {
        return Jwts.builder()
            .subject(username)
            .claim("role", "ROLE_" + role) 
            .issuedAt(new Date()) // Set issued time
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1-hour validity
            .signWith(secretKey) // Use the correct key signing method
            .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parser()
                .verifyWith(secretKey) // Updated method for verification
                .build()
                .parseSignedClaims(token) // Updated parsing method
                .getPayload()
                .getSubject(); // Extract username
        } catch (Exception e) {
            return null; // Handle invalid token gracefully
        }
    }
    
    public String extractRole(String token)
    {
    	try {
    		Claims claims = Jwts.parser()
    				.verifyWith(secretKey)
    				.build()
    				.parseSignedClaims(token)
    				.getPayload();			
    		return claims.get("role",String.class);		
    	}catch(Exception e)
    	{
    		return null;
    	}
    }


    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                .verifyWith(secretKey) // Updated method for verification
                .build()
                .parseSignedClaims(token) // Updated parsing method
                .getPayload();
            return claims.getExpiration().before(new Date()); // Check expiration
        } catch (Exception e) {
            return true; // If parsing fails, consider token expired
        }
    }

}

