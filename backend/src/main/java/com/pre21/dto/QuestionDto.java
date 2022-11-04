package com.pre21.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {


    @Getter
    @AllArgsConstructor
    public static class Like {
        private Long userId;
        private boolean likeYn;
        private boolean unlikeYn;
    }


    // 질문 상제 조회 Dto
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetResponseDto {
        private Long questionId;    // 질문 Id
        private String title;   // 질문 제목
        private String contents;    // 질문 내용
        private List<QuestionsTagsResponseDto> tags;   // 질문에 사용한 태그 정보
        private int vote;   // 질문 추천수
        private List<AnswersDto.ResponseDto> answers;   // 질문에 달린 답글 정보
        private int views;  // 질문 조회수
        private LocalDateTime createdAt;    // 질문 생성 일자
        private String nickname;    // 질문을 생성한 유저 닉네임
        private int answerCount;    // 질문에 달린 답변 개수
        private List<QuestionCommentResponseDto> comments;  // 질문에 달린 댓글 정보
        private List<QuestionBookmarkResponseDto> bookmarks;    // 북마크 유저 정보
        private List<QuestionLikeResponseDto> questionsLikes;   // 질문 좋아요,싫어요 유저 정보
    }

    // 질문 전체 조회
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GetResponseDtos {
        private Long id;
        private String title;
        private String contents;
        private List<QuestionsTagsResponseDto> tags;
        private int vote;
        private boolean chooseYn;
        private int views;
        private LocalDateTime createdAt;
        private int answerCount;
    }
}
