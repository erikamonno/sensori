package com.example.sensor.utils;

import com.example.sensor.model.entities.Utente;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String secretKeyPath;
    private byte[] secretKey;
    private long ONE_HOUR_IN_MILLIS = 60*60*1000;

    private JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    @PostConstruct
    private void initialize() throws IOException {
        log.info("JwtUtil.initialize()");
        this.secretKey = null;
        this.secretKey = Files.readAllBytes(Path.of(secretKeyPath));
        this.jwtParser = Jwts.parser().setSigningKey(getSigningKey()).build();
    }

    public String createToken(Utente user) {
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(ONE_HOUR_IN_MILLIS));
        Claims claims = Jwts.claims()
                .subject(user.getUsername())
                .add("firstName",user.getNome())
                .add("lastName",user.getCognome())
                .add("roles", user.getAdmin() ? RoleConstants.ADMIN : RoleConstants.USER)
                .build();
        return Jwts.builder()
                .claims(claims)
                .expiration(tokenValidity)
                .signWith(getSigningKey())
                .compact();
    }
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey);
    }
    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    private List<String> getRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }


}