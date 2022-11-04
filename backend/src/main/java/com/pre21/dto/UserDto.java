package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UserInfo {
        private Long id;
        private String nickname;
        private LocalDateTime createdAt;
    }
}
