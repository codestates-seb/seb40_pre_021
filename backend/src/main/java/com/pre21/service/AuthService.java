package com.pre21.service;

import com.pre21.dto.AuthDto;
import com.pre21.entity.RefreshToken;
import com.pre21.entity.User;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.RefreshTokenRepository;
import com.pre21.repository.AuthRepository;
import com.pre21.security.jwt.JwtTokenizer;
import com.pre21.security.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;


    /**
     * 회원가입 비즈니스 로직 메서드
     * @param user User(사용자 엔티티)
     * @author mozzi327
     */
    public void createUser(User user) {
        verifyExistsEmail(user.getEmail());
        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        List<String> createRole = authorityUtils.createRoles(user.getEmail());
        user.setRoles(createRole);
        authRepository.save(user);
    }


    /**
     * 사용자 로그아웃 비즈니스 로직 메서드
     * @param refreshToken 리프레시 토큰
     * @author mozzi327
     */
    public void logoutUser(String refreshToken) {
        // 토큰이 있는지 확인한 후 삭제
        RefreshToken findToken = checkExistToken(refreshToken);
        refreshTokenRepository.delete(findToken);
    }


    /**
     * 액세스 토큰 리이슈 비즈니스 로직 메서드
     * @param refreshToken 리프레시 토큰
     * @param userId 사용자 식별자
     * @return AuthoDto.Response
     * @author mozzi327
     */
    public AuthDto.Response reIssueAccessToken(String refreshToken, Long userId) {
        RefreshToken findRefreshToken = checkExistToken(refreshToken);

        User findUser = authRepository
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


    /**
     * 리프레시 토큰 데이터베이스 존재 유무 확인 메서드
     * @param refreshToken 리프레시 토큰
     * @return RefreshToken(조회 리프레시토큰)
     * @author mozzi327
     */
    private RefreshToken checkExistToken(String refreshToken) {
        return refreshTokenRepository
                .findRefreshTokenByTokenValue(refreshToken)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TOKEN_NOT_FOUND));
    }


    /**
     * 토큰 Response 생성 메서드
     * @param email
     * @param roles
     * @param refreshToken
     * @return AuthDto.Token(액세스 토큰, 리프레시 토큰)
     * @author mozzi327
     */
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
        Optional<User> user = authRepository.findByEmail(email);
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
                authRepository.findById(userId);
        return optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }
}
