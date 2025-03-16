package ru.developeerz.user_service.core.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.developeerz.user_service.core.entity.AuthorityId;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);

    private final SecretKey jwtAccessSecret;

    private final SecretKey jwtRefreshSecret;

    public JwtProvider(
            @Value("${jwt.secret.access}") String jwtAccessSecret,
            @Value("${jwt.secret.refresh}") String jwtRefreshSecret
    ) throws NoSuchAlgorithmException {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
    }

    public String generateAccessToken(long userId, Set<AuthorityId> authorities) {
        LocalDateTime now = LocalDateTime.now();
        Instant accessExpirationInstant = now.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant();
        Date accessExpiration = Date.from(accessExpirationInstant);

        return Jwts.builder()
                .claim("id", userId)
                .claim("authority", authorities)
                .expiration(accessExpiration)
                .signWith(jwtAccessSecret)
                .compact();
    }

    public String generateRefreshToken(long userId) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);

        return Jwts.builder()
                .claim("id", userId)
                .expiration(refreshExpiration)
                .signWith(jwtRefreshSecret)
                .compact();
    }

    private boolean isValidAccessToken(String token) {
        return isValidToken(token, jwtAccessSecret);
    }

    private boolean isValidRefreshToken(String token) {
        return isValidToken(token, jwtRefreshSecret);
    }

    private boolean isValidToken(String token, SecretKey secret) {
        try {
            Jwts.parser()
                    .verifyWith(secret)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
