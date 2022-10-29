package com.pre21.service;

import com.pre21.dto.AuthDto;
import com.pre21.entity.RefreshToken;
import com.pre21.entity.User;
import com.pre21.repository.RefreshTokenRepository;
import com.pre21.repository.UserRepository;
import com.pre21.security.jwt.JwtTokenizer;
import com.pre21.security.utils.CustomAuthorityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenizer jwtTokenizer;

    public void createUser(User user) {
        verifyExistsEmail(user.getEmail());
        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepository.save(user);
    }

    private void verifyExistsEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new RuntimeException("회원 이미 존재");
        }
    }

    public void logoutUser(String email) {
        refreshTokenRepository.deleteRefreshTokenByTokenEmail(email);
    }

    public AuthDto.Token reIssueAccessToken(String refreshToken) {
        RefreshToken findRefreshToken = checkExistToken(refreshToken);

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> claims = jwtTokenizer.getClaims(refreshToken, base64EncodedSecretKey).getBody();

        String email = (String) claims.get("sub");
        List<String> roles = userRepository.findByEmail(email).get().getRoles();

        AuthDto.Token responseToken = createReIssueToken(email, roles, findRefreshToken.getTokenValue());

        refreshTokenRepository.deleteRefreshTokenByTokenEmail(email);
        refreshTokenRepository.save(new RefreshToken(responseToken.getRefreshToken(), email));

        return responseToken;
    }


    public void deleteDatabaseRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteRefreshTokenByTokenValue(refreshToken);
    }


    private RefreshToken checkExistToken(String refreshToken) {
        return refreshTokenRepository
                .findRefreshTokenByTokenValue(refreshToken)
                .orElseThrow(() -> new MalformedJwtException("유효하지 않은 토큰입니다"));
    }



    private AuthDto.Token createReIssueToken(String email, List<String> roles, String refreshToken) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", email);
        claims.put("roles", roles);

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String accessToken = jwtTokenizer.generateAccessToken(claims, email, expiration, base64EncodedSecretKey);
        accessToken = "Bearer " + accessToken;

        return AuthDto.Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
