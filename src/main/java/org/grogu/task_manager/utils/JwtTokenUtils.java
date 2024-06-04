package org.grogu.task_manager.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.*;

@Component
public class JwtTokenUtils {
    @Value("${token.secret}")
    private String secret;

    @Value("${token.lifetime}")
    private Duration jwtLifeTime;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> payload = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        payload.put("roles", roles);
        Date tokenStartTime = new Date();
        Date tokenEndTime = new Date(tokenStartTime.getTime() + jwtLifeTime.toMillis());

        return Jwts.builder()
                .claims(payload)
                .subject(userDetails.getUsername())
                .issuedAt(tokenStartTime)
                .expiration(tokenEndTime)
                .signWith(getKey())
                .compact();
    }

    public String getUserName(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    private Key getKey() {
        byte[] key = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(key);
    }

    private Claims getAllClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
