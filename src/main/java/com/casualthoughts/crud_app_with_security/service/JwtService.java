package com.casualthoughts.crud_app_with_security.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value("${app.jwt.secret}")
    private String SECRET;
    @Value("${app.jwt.expiration}")
    private long EXPIRATION;

    public String extractUsername(String jwt) {
        return null;
    }

    public boolean isValidToken(String jwt, UserDetails userDetails) {
        return false;
    }
}
