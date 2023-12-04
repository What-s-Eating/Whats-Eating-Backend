package whatseating.backend.global.security.jwt;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import whatseating.backend.domain.user.user.domain.User;
import whatseating.backend.global.security.jwt.exception.ExpiredTokenException;
import whatseating.backend.global.security.jwt.exception.InvalidTokenException;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    // 객체 초기화, SECRET_KEY를 Base64로 인코딩
    @PostConstruct
    public void init() {
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    public String createAccessToken(User user) {
        return createToken(user, 24 * 60 * 60 * 1000L);
    }

    public String createRefreshToken(User user) {
        return createToken(user, 7 * 24 * 60 * 60 * 1000L);
    }

    public String createToken(User user, long tokenValidTime) {
        Claims claims = Jwts.claims();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }

    public String getUserId(String token) {
        return extractAllClaims(token)
                .get("userId", String.class);
    }
}
