package com.pre21.service;

import com.pre21.dto.AuthDto;
import com.pre21.entity.RefreshToken;
import com.pre21.entity.User;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
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

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;


    // 회원가입
    public User createUser(User user) {
        verifyExistsEmail(user.getEmail());
        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        List<String> createRole = authorityUtils.createRoles(user.getEmail());
        user.setRoles(createRole);
        User savedUser = userRepository.save(user);

        return savedUser;
    }


    // 사용자 로그아웃
    public void logoutUser(String refreshToken) {
        // 토큰이 있는지 확인한 후 삭제
        RefreshToken findToken = checkExistToken(refreshToken);
        refreshTokenRepository.delete(findToken);
    }


    // 토큰 생성 메서드를 호출하여 리스폰즈를 생성 후 리턴
    public AuthDto.Response reIssueAccessToken(String refreshToken, Long userId) {
        RefreshToken findRefreshToken = checkExistToken(refreshToken);

        User findUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        String email = findUser.getEmail();
        List<String> roles = findUser.getRoles();

        AuthDto.Token reIssueToken = createReIssueToken(email, roles, findRefreshToken.getTokenValue());

        AuthDto.Response response = AuthDto.Response.builder()
                .accessToken(reIssueToken.getAccessToken())
                .nickname(findUser.getNickname())
                .email(findUser.getEmail())
                .build();

        refreshTokenRepository.deleteRefreshTokenByUserId(userId);
        refreshTokenRepository.save(new RefreshToken(reIssueToken.getRefreshToken(), email, userId));

        return response;
    }


    // 토큰이 존재하는지 확인
    private RefreshToken checkExistToken(String refreshToken) {
        return refreshTokenRepository
                .findRefreshTokenByTokenValue(refreshToken)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TOKEN_NOT_FOUND));
    }


    // 토큰 재생성 로직
    private AuthDto.Token createReIssueToken(String email, List<String> roles, String refreshToken) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", email);
        claims.put("roles", roles);

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String accessToken = jwtTokenizer.generateAccessToken(claims, email, expiration, base64EncodedSecretKey);

        return AuthDto.Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    // 해당 이메일이 존재하는지 확인
    private void verifyExistsEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        }
    }

    public User findUser(Long userId) {
        User findUser = findVerifiedUser(userId);
        return findUser;
    }

    /**
     * @param userId Long 타입의 사용자 Id 값을 받아서 User 객체를 찾고 반환합니다.
     * @return User
     * @author dev32user
     */
    private User findVerifiedUser(Long userId) {
        Optional<User> optionalUser =
                userRepository.findById(userId);
        return optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }
}
