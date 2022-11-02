package com.pre21.dto;

import com.pre21.entity.Adoption;
import com.pre21.entity.AnswerLikes;
import com.pre21.entity.Questions;
import com.pre21.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetResponseDto {
        private Long answerId;
        private String contents;
        private int vote;
        private Adoption adoption;
        private String imageUrl;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private User user;
        private List<AnswerLikes> answerLikes;
        private Questions questions;
    }
}
