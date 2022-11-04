package com.pre21.controller;

import com.pre21.dto.AuthDto;
import com.pre21.mapper.UserMapper;
import com.pre21.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.pre21.security.utils.ControllerConstants.USER_ID;
import static com.pre21.security.utils.JwtConstants.REFRESH_TOKEN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final UserMapper mapper;

    /**
     * 회원가입을 위한 컨트롤러 호출 메서드
     * @param requestBody Auth.Join(회원가입 DTO)
     * @author mozzi327
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signup")
    public void joinUser(@RequestBody AuthDto.Join requestBody) {
        authService.createUser(mapper.joinToUserEntity(requestBody));
    }


    /**
     * 사용자 로그아웃을 위한 컨트롤러 호출 메서드
     * @param refreshToken 리프레시 토큰
     * @param response HttpServletResponse
     * @author mozzi327
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/logout")
    public void logoutUser(@CookieValue(name = REFRESH_TOKEN, required = true) String refreshToken,
                           HttpServletResponse response) {
        authService.logoutUser(refreshToken);
        Cookie deleteToken = new Cookie(REFRESH_TOKEN, null);
        deleteToken.setMaxAge(0);
        deleteToken.setPath("/");

        Cookie deleteUserId = new Cookie("userId", null);
        deleteUserId.setMaxAge(0);
        deleteUserId.setPath("/");

        response.addCookie(deleteToken);
        response.addCookie(deleteUserId);
    }


    /**
     * 액세스 토큰 리이슈를 위한 컨트롤러 호출 메서드
     * @param refreshToken 리프레시 토큰
     * @param userId 사용자 식별자
     * @return ResponsEntity(액세스토큰)
     * @author mozzi327
     */
    @GetMapping("/refresh")
    public ResponseEntity reIssueAccessToken (
            @CookieValue(name = REFRESH_TOKEN, required = true) String refreshToken,
            @CookieValue(name = USER_ID, required = true) Long userId) {
        AuthDto.Response resultToken = authService.reIssueAccessToken(refreshToken, userId);
        return ResponseEntity.ok(resultToken);
    }
}
