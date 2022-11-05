package com.pre21.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pre21.dto.AuthDto;
import com.pre21.entity.User;
import com.pre21.response.ErrorResponse;
import com.pre21.security.dto.LoginDto;
import com.pre21.security.jwt.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;


    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = objectMapper.readValue(req.getInputStream(), LoginDto.class);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }


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
        String encodeDomain = URLEncoder.encode("http://2ne1-client.s3-website.ap-northeast-2.amazonaws.com", StandardCharsets.UTF_8);

        jwtTokenizer.savedRefreshToken(refreshToken, email, findUser.getId());
        sendResponse(accessToken, email, res, encodeDomain);

        String encodedRefresh = URLEncoder.encode(refreshToken, "UTF-8");
//        Cookie cookie = new Cookie("RefreshToken", encodedRefresh);
//
//        cookie.setPath("http://2ne1-client.s3-website.ap-northeast-2.amazonaws.com");
//        cookie.setHttpOnly(true);
////        cookie.setSecure(true);
//
//        res.addCookie(cookie);
        ResponseCookie cookie = ResponseCookie.from("Lax", "Lax")
                .path("/")
                .sameSite("Lax")
                .httpOnly(true)
                .domain(encodeDomain)
                .build();
        res.addHeader("Set-Header", cookie.toString());

        this.getSuccessHandler().onAuthenticationSuccess(req, res, authResult);
    }


    private String delegateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getEmail());
        claims.put("roles", user.getRoles());

        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }


    private String delegateRefreshToken(User user) {
        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

    private void sendResponse(String accessToken,
                              String email, HttpServletResponse res,
                              String domain) throws IOException {
        Gson gson = new Gson();
        User findUser = jwtTokenizer.findUserByEmail(email);
//        Cookie cookie = new Cookie("userId", findUser.getId().toString());
//        cookie.setPath("http://2ne1-client.s3-website.ap-northeast-2.amazonaws.com");
//        cookie.setHttpOnly(true);
////        cookie.setSecure(true);
//
//        res.addCookie(cookie);
        ResponseCookie cookie = ResponseCookie.from("Lax", "Lax")
                .path("/")
                .sameSite("Lax")
                .httpOnly(true)
                .domain(domain)
                .build();
        res.addHeader("Set-Cookie", cookie.toString());

        AuthDto.Response response = AuthDto.Response.builder()
                .accessToken(accessToken)
                .nickname(findUser.getNickname())
                .email(email)
                .build();

        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.getWriter().write(gson.toJson(response, AuthDto.Response.class));
    }
}
