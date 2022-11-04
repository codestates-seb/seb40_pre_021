package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class AuthDto {
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

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Token {
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private String accessToken;
        private String nickname;
        private String email;
    }
}
