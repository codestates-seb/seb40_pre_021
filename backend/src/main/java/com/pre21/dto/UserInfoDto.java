package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoDto {
    private Long id;
    private String nickname;
    private LocalDateTime createdAt;
}
