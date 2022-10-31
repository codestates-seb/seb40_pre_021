package com.pre21.controller;

import com.pre21.dto.AuthDto;
import com.pre21.dto.UserDto;
import com.pre21.entity.User;
import com.pre21.mapper.UserMapper;
import com.pre21.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pre21.security.utils.JwtConstants.REFRESH_TOKEN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signup")
    public void joinUser(@RequestBody UserDto.Join requestBody) {
        User user = new User(requestBody.getNickname(), requestBody.getEmail(), requestBody.getPassword());
        userService.createUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/logout/{email}")
    public void logoutUser(@PathVariable("email") String email) {
        userService.logoutUser(email);
    }


    @PostMapping("/token/refresh")
    public ResponseEntity<AuthDto.Token> reIssueAccessToken(@RequestHeader(REFRESH_TOKEN) String refreshToken) {
        AuthDto.Token response = userService.reIssueAccessToken(refreshToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/token/expire")
    public void expiredRefreshToken(@RequestHeader(REFRESH_TOKEN) String refreshToken) {
        userService.deleteDatabaseRefreshToken(refreshToken);
    }
}
