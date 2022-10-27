package com.pre21.controller;

import com.pre21.dto.UserDto;
import com.pre21.entity.User;
import com.pre21.mapper.UserMapper;
import com.pre21.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signup")
    public void joinUser(@RequestBody UserDto.Join requestBody) {
        User user = mapper.joinToUserEntity(requestBody);
        userService.createUser(user);
    }
}
