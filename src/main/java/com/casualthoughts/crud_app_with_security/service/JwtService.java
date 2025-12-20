package com.casualthoughts.crud_app_with_security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${app.jwt.secret}")
    private String SECRET;
    @Value("${app.jwt.expiration}")
    private long EXPIRATION;

    /// JwtAuthFilter uses extractUsername and isValidToken.
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public boolean isValidToken(String jwt, UserDetails userDetails ){
       String username = extractUsername(jwt);
       return (username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    /// AuthController uses generateToken.
    public String generateToken(String username) {
        Map<String, Object> claims= new HashMap<>();
        return createToken(username,claims);
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt){
        return extractClaim(jwt,Claims::getExpiration);
    }

    private Claims extractAllClaims ( String jwt){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    private <T> T extractClaim(String jwt, Function<Claims,T> claimsResolver) {
        Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }


    private String createToken(String username, Map<String, Object> claims){
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(getSigningKey(),Jwts.SIG.HS256)
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
