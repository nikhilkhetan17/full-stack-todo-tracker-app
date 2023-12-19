package com.niit.todo.userAuthenticationService.security;

import com.niit.todo.userAuthenticationService.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGenerationImpl implements SecurityTokenGeneration {


    public String createToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("emailId", user.getEmailId());
        return generateToken(claims, user.getEmailId());
    }


    public String generateToken(Map<String, Object> claims, String subject) {
        String jwtToken = Jwts.builder()
                .setIssuer("TodoTracker")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        return jwtToken;
    }
}


