package com.Backend.java.demo.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import java.util.Date;

@Configuration
public class JwtTokenProvider {
    public String generateToken(Authentication authentication){
        String email= authentication.getName();
        Date getCurrentDate=new Date();
        Date expireDate=new Date(getCurrentDate.getTime()+3600000);
        String token= Jwts.builder().setSubject(email).setIssuedAt(getCurrentDate).setExpiration(expireDate).signWith(SignatureAlgorithm.HS512,"SecurityKey").compact();
        return token;
    }
    public String getEmailFromJwt(String token){
        Claims claims=Jwts.parser().setSigningKey("SecurityKey").parseClaimsJwt(token).getBody();
        return claims.getSubject();
    }
}

