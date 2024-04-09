package com.tangtang.polingo.security.service;

import com.tangtang.polingo.security.exception.JwtAuthenticationException;
import com.tangtang.polingo.security.property.JwtProperties;
import com.tangtang.polingo.user.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;

    public String createToken(User user) {
        return createToken(String.format("%s:%s", user.getId(), "MEMBER"));
    }

    private String createToken(String userSpecification) {
        String secretKey = jwtProperties.getSecretKey();
        long expirationHours = jwtProperties.getExpirationHours();
        String issuer = jwtProperties.getIssuer();

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,
                        new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .setSubject(userSpecification)
                .setIssuer(issuer)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))
                .compact();
    }

    public Optional<String> extractTokenFromHeader(HttpServletRequest request) {
        Optional<String> authorizationHeader = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION));

        if (authorizationHeader.isEmpty()) {
            return Optional.empty();
        }

        if (!authorizationHeader.get().startsWith("Bearer ")) {
            throw new JwtAuthenticationException("토큰 값 앞에 Bearer가 있어야 합니다!");
        }

        return Optional.of(authorizationHeader.get().substring(7));
    }


    public String validateTokenAndGetSubject(String token) {
        try {
            return validateToken(token);
        } catch (JwtException e) {
            throw new JwtAuthenticationException(generateErrorMessage(e));
        }
    }

    private String validateToken(String token) {
        String secretKey = jwtProperties.getSecretKey();
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private String generateErrorMessage(JwtException e) {
        if (e instanceof SignatureException) {
            return "시그니처가 일치하지 않습니다!";
        }
        if (e instanceof ExpiredJwtException) {
            return "토큰이 만료되었습니다!";
        }
        if (e instanceof MalformedJwtException) {
            return "잘못된 형식의 토큰입니다!";
        }
        if (e instanceof UnsupportedJwtException) {
            return "지원하지 않는 토큰입니다!";
        }
        return "요청 형식은 맞았으나 올바른 토큰이 아닙니다!\n" + e.getMessage();
    }
}
