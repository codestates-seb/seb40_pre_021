package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AnswersDto {
    // 답변 생성 POST
    @Getter
    @AllArgsConstructor
    public static class Post {
        // 유저 이메일
        private String email;
        // 답변 내용
        private String contents;
    }
}
