package com.pre21.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.pre21.dto.AuthDto;
import com.pre21.entity.User;
import com.pre21.security.dto.LoginDto;
import com.pre21.security.jwt.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.pre21.security.utils.JwtConstants.*;


/**
 * 사용자 인증을 위한 Authentication filter 클래스
 * @author mozzi327
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;


    /**
     * request에 요청 스트림을 추출해서 권한 인증을 authenticationManager에게 전달하는 메서드
     * @param req 요청
     * @param res 응답
     * @return Authentication
     * @author mozzi327
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = objectMapper.readValue(req.getInputStream(), LoginDto.class);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }


    /**
     * 인증 성공 시 엑세스 토큰을 응답 바디에, 리프레시 토큰과 사용자 식별자를 쿠키에 담아주는 메서드
     * @param req 요청
     * @param res 응답
     * @param chain 필터체인
     * @param authResult 인증 객체
     * @author mozzi327
     */
    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication authResult) {
        User user = (User) authResult.getPrincipal();
        String email = user.getEmail();
        User findUser = jwtTokenizer.findUserByEmail(email);

        String accessToken = delegateAccessToken(user);
        String refreshToken = delegateRefreshToken(findUser);

        jwtTokenizer.savedRefreshToken(refreshToken, email, findUser.getId());

        String encodedRefresh = URLEncoder.encode(refreshToken, StandardCharsets.UTF_8);
        ResponseCookie refCookie = ResponseCookie.from(REFRESH_TOKEN, encodedRefresh)
                .maxAge(7 * 24 * 60 * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        res.setHeader(SET_COOKIE, refCookie.toString());


        sendResponse(accessToken, email, res);
        this.getSuccessHandler().onAuthenticationSuccess(req, res, authResult);
    }


    /**
     * 사용자 인증 성공시 엑세스 토큰을 발급해주는 메서드
     * @param user 사용자 정보(principal)
     * @return 액세스 토큰
     * @author mozzi327
     */
    private String delegateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getEmail());
        claims.put("roles", user.getRoles());

        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        return jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);
    }


    private String delegateRefreshToken(User user) {
        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        return jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);
    }

    /**
     * 응답 바디에 엑세스 토큰을 저장, 사용자 식별자를 쿠키에 담아주는 메서드(로직 분리용)
     * @param accessToken 액세스 토큰
     * @param email 사용자 이메일
     * @param res 응답
     * @throws IOException 입력 예외
     * @author mozzi327
     */
    private void sendResponse(String accessToken,
                              String email, HttpServletResponse res) throws IOException {
        Gson gson = new Gson();
        User findUser = jwtTokenizer.findUserByEmail(email);

        ResponseCookie cookie = ResponseCookie.from(USER_ID, findUser.getId().toString())
                .maxAge(7 * 24 * 60 * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        res.addHeader(SET_COOKIE, cookie.toString());

        AuthDto.Response response = AuthDto.Response.builder()
                .accessToken(accessToken)
                .nickname(findUser.getNickname())
                .email(email)
                .build();

        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.getWriter().write(gson.toJson(response, AuthDto.Response.class));
    }
}
