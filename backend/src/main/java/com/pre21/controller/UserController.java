package com.pre21.controller;

import com.pre21.dto.AuthDto;
import com.pre21.dto.UserDto;
import com.pre21.entity.User;
import com.pre21.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.pre21.security.utils.JwtConstants.REFRESH_TOKEN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signup")
    public void joinUser(@RequestBody UserDto.Join requestBody) {
        User user = new User(requestBody.getNickname(),
                requestBody.getEmail(),
                requestBody.getPassword());
        userService.createUser(user);
    }


    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/logout")
    public void logoutUser(@CookieValue(name = REFRESH_TOKEN, required = true) String refreshToken,
                           HttpServletResponse response) {
        userService.logoutUser(refreshToken);
        Cookie deleteToken = new Cookie(REFRESH_TOKEN, null);
        deleteToken.setMaxAge(0);
        deleteToken.setPath("/");
        response.addCookie(deleteToken);
    }


    @GetMapping("/refresh")
    public ResponseEntity reIssueAccessToken (
            @CookieValue(name = REFRESH_TOKEN, required = true) String refreshToken) {

        AuthDto.Response resultToken = userService.reIssueAccessToken(refreshToken);

        return ResponseEntity.ok(resultToken);
    }
}
