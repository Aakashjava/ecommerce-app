package com.aakash.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

	SecretKey key=Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//	public String generetToken(Authentication auth) {
//		String jwt=Jwts.builder()
//				.setIssuedAt(new Date())
//				.setExpiration(new Date(new Date().getTime()+84600000))
//				.claim("email", auth.getName())
//				.signWith(key).compact();
//		return jwt;
//		
//	}
	  public String generateToken(Authentication auth) {
	        return Jwts.builder()
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 84600000)) // 24 hours
	                .claim("email", auth.getName())
	                .signWith(key)
	                .compact();
	    }
	  
	public String getEmailFromToken(String jwt) {
		jwt=jwt.substring(7);
		Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		String email=String.valueOf(claims.get("email"));
		return email;
	}
}
