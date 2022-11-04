package com.pre21.security.jwt;

import com.pre21.dto.AuthDto;
import com.pre21.entity.RefreshToken;
import com.pre21.entity.User;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.RefreshTokenRepository;
import com.pre21.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenizer {

    @Getter
//    @Value("${JWT_SECRET_KEY}")
    private String secretKey = "ewqiopasdasdhjqwjkdqwdjkashdjkashdasjdhqweqeqweqwdasjxasxqweu123dhasd3423hasjdk";

    @Getter
    private int accessTokenExpirationMinutes = 15;

    @Getter
    private int refreshTokenExpirationMinutes = 30;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;


    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {

        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA).getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String subject,
                                       Date expiration,
                                       String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA).getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    // 검증 후, Claims를 반환하는 용도
    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(jws);

        return claims;
    }

    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }

    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }


    // 리프레시 토큰 유무 확인
    public void verifiedExistRefresh(String refreshToken) {
        Optional<RefreshToken> findRefreshToken = refreshTokenRepository.findRefreshTokenByTokenValue(refreshToken);
        if (!refreshToken.equals(findRefreshToken.get().getTokenValue()))
            throw new BusinessLogicException(ExceptionCode.TOKEN_NOT_FOUND);
    }


    // 리프레시 토큰 저장
    public void savedRefreshToken(String refreshToken, String email, Long userId) {
        Optional<RefreshToken> findRefreshToken = refreshTokenRepository.findRefreshTokenByTokenEmail(email);
        findRefreshToken.ifPresent(refreshTokenRepository::delete);
        refreshTokenRepository.save(new RefreshToken(refreshToken, email, userId));
    }

    public User findUserByEmail(String email) {
        return userRepository
                .findUserByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }


    public String isExistRefresh(Cookie[] cookies) {
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("RefreshToken")) return cookies[i].getValue();
        }
        throw new BusinessLogicException(ExceptionCode.COOKIE_NOT_FOUND);
    }
}
