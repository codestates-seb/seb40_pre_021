package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {

    @Getter
    @AllArgsConstructor
    public static class Join {
        @Nullable
        private String nickname;

        @Nullable
        private String email;

        @Nullable
        private String password;
    }
}
