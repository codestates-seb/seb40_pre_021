package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {

    @Getter
    @AllArgsConstructor
    public static class Join {
        @NotBlank
        private String nickname;

        @NotBlank
        private String email;

        @NotBlank
        private String password;
    }
}
