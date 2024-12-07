package com.acing.techmaps.security.service;

import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.web.exception.HttpException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {
    @Value("${security.jwt.secretKey}")
    private String secretKey;

    private final ConcurrentHashMap<String, Instant> invalidatedTokens = new ConcurrentHashMap<>();

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("techmaps-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while generating token", e.getMessage());
        }
    }

    public String validateToken(String token) {
        try {
            if (isTokenInvalidated(token)) {
                throw new HttpException(HttpStatus.UNAUTHORIZED, "Token has been invalidated");
            }

            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("techmaps-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Invalid token", exception.getMessage());
        }
    }

    public void invalidateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Instant expiration = JWT.require(algorithm)
                    .withIssuer("techmaps-api")
                    .build()
                    .verify(token)
                    .getExpiresAt()
                    .toInstant();

            invalidatedTokens.put(token, expiration);
        } catch (JWTVerificationException e) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Invalid token", e.getMessage());
        }
    }

    private boolean isTokenInvalidated(String token) {
        Instant expiration = invalidatedTokens.get(token);
        if (expiration == null) return false;

        if (expiration.isBefore(Instant.now())) {
            invalidatedTokens.remove(token);
            return false;
        }

        return true;
    }

    private Instant genExpirationDate() {
        return Instant.now().plusSeconds(1800);
    }

    public String refreshToken(String token) {
        String email = validateToken(token);
        return generateToken(User.createWithEmail(email));
    }
}
