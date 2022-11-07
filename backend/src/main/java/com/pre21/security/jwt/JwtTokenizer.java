package com.pre21.security.jwt;

import com.pre21.entity.RefreshToken;
import com.pre21.entity.User;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.RefreshTokenRepository;
import com.pre21.repository.AuthRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.*;

import static com.pre21.security.utils.JwtConstants.*;

/**
 * 실질적인 토큰 생성을 관여하는 JwtUtil 클래스
 * @author mozzi327
 */

@Component
@RequiredArgsConstructor
public class JwtTokenizer {

    @Getter
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;

    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthRepository authRepository;


    /**
     * 서버 환경변수에 저장된 시크릿 키를 인코딩하여 반환해주는 메서드
     * @param secretKey 시크릿 키
     * @return 인코딩된 시크릿 키
     * @author mozzi327
     */
    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 액세스 토큰을 발급하는 메서드
     * @param claims claims 정보
     * @param subject 사용자 subject(이메일)
     * @param expiration 토큰 만료시간
     * @param base64EncodedSecretKey base64 인코딩 키
     * @return 액세스 토큰
     * @author mozzi327
     */
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

    /**
     * 리프레시 토큰을 발급하는 메서드
     * @param subject 사용자 subject(이메일)
     * @param expiration 토큰 만료시간
     * @param base64EncodedSecretKey base64 인코딩 키
     * @return 리프레시 토큰
     * @author mozzi327
     */
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

    /**
     * 검증 후 Jws(Claims)를 반환해주는 메서드
     * @param jws
     * @param base64EncodedSecretKey base64 인코딩 키
     * @return Jws
     * @author mozzi327
     */
    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(jws);
    }

    /**
     * 토큰의 만료 시간을 반환해주는 메서드
     * @param expirationMinutes 서버에 저장된 만료 시간 값
     * @return Date
     * @author mozzi327
     */
    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        return calendar.getTime();
    }

    /**
     * base64로 인코딩된 키를 Key 객체로 만들어 반환하는 메서드
     * @param base64EncodedSecretKey base64 인코딩 키
     * @return Key
     * @author mozzi327
     */
    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    /**
     * 리프레시 토큰이 데이터베이스에 존재하는지 유무를 확인하는 메서드
     * - 리프레시 토큰 값이 같은지도 확인한다.
     * @param refreshToken 리프레시 토큰
     * @author mozzi327
     */
    public void verifiedExistRefresh(String refreshToken) {
        RefreshToken findRefreshToken = refreshTokenRepository
                .findRefreshTokenByTokenValue(refreshToken)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TOKEN_NOT_FOUND));

        // 확인한 토큰이 서로 다르면 예외 발생
        if (!refreshToken.equals(findRefreshToken.getTokenValue()))
            throw new BusinessLogicException(ExceptionCode.TOKEN_NOT_FOUND);
    }


    /**
     * 리프레시 토큰은 데이터베이스에 저장하는 메서드
     * - 데이터베이스에 리프레시 토큰이 존재하는지 확인하고, 존재한다면 삭제후 저장
     * @param refreshToken 리프레시 토큰
     * @param email 사용자 이메일
     * @param userId 사용자 식별자
     * @author mozzi327
     */
    public void savedRefreshToken(String refreshToken, String email, Long userId) {
        Optional<RefreshToken> findRefreshToken = refreshTokenRepository.findByUserId(userId);
        findRefreshToken.ifPresent(refreshTokenRepository::delete);
        refreshTokenRepository.save(new RefreshToken(refreshToken, email, userId));
    }

    /**
     * 인증 성공 시 사용되는 사용자 정보(엔티티)를 반환하는 메서드
     * - save를 한번 하는 이유는 최근 로그인 날짜를 갱신하기 위함
     * @param email 사용자 이메일
     * @return 사용자 정보
     * @author mozzi327
     */
    public User findUserByEmail(String email) {
        User findUser = authRepository
                .findUserByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        findUser.setLatestLogin(LocalDateTime.now());
        authRepository.save(findUser);
        return findUser;
    }


    /**
     * 전달받은 쿠키 값 중 RefreshToken이 있는지 확인하는 메서드
     * 존재한다면 해당 토큰 값을 반환한다.
     * @param cookies (쿠키 배열)
     * @return 리프레시 토큰
     * @author mozzi327
     */
    public String isExistRefresh(Cookie[] cookies) {
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(REFRESH_TOKEN)) return cookies[i].getValue();
        }
        throw new BusinessLogicException(ExceptionCode.COOKIE_NOT_FOUND);
    }
}
