package com.pre21.security.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
