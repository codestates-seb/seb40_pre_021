package com.pre21.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


/**
 * 로그인에 사용되는 Dto 클래스
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
