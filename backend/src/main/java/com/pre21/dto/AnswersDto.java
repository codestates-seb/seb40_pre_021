package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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

    // 질문 조회 시 생성되어 있는 답변 조회
    @Getter
    @Builder
    @AllArgsConstructor
    public static class ResponseDto {
        private Long answerId;
        private String contents;
    }
}
