package com.pre21.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    //질문 생성 Dto
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Post {
        private String title;
        private String contents;
        private List<String> tags;
    }
    // 질문 수정 Dto
    @Getter
    @AllArgsConstructor
    public static class Patch {
        private String title;
        private String contents;
        private List<String> tags;
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
        private List<QuestionDto.TagResponse> questionsTags;   // 질문에 사용한 태그 정보
        private int vote;   // 질문 추천수
        private List<AnswerDto.Response> answers;   // 질문에 달린 답글 정보
        private int views;  // 질문 조회수
        private LocalDateTime createdAt;    // 질문 생성 일자
        private String nickname;    // 질문을 생성한 유저 닉네임
        private int answerCount;    // 질문에 달린 답변 개수
        private List<QuestionDto.CommentResponse> comments;  // 질문에 달린 댓글 정보
        private List<QuestionDto.BookmarkResponse> bookmarks;    // 북마크 유저 정보
        private List<QuestionDto.LikeResponse> questionsLikes;   // 질문 좋아요,싫어요 유저 정보
    }

    // 질문 전체 조회 Dto1
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GetResponseDtos {
        private Long id;
        private String title;
        private String contents;
        private List<QuestionDto.TagResponse> questionsTags;
        private int vote;
        private boolean chooseYn;
        private int views;
        private LocalDateTime createdAt;
        private int answerCount;
    }

    // 질문 북마크 Dto
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookmarkResponse {
        private Long id;
        private Long userId;
        private String nickname;
    }

    // 질문 댓글 생성 Dto
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public static class CommentPost {
        private String comments;
    }

    // 질문 댓글 응답 Dto
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponse {
        private Long id;
        private String comments;
        private LocalDateTime createdAt;
        private String nickname;
    }

    // 좋아요, 싫어요 생성 Dto
    @Getter
    @AllArgsConstructor
    public static class Like {
        private boolean likeYn;
        private boolean unlikeYn;
    }

    // 질문 좋아요 응답 Dto
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LikeResponse {
        private Long userId;
        private String nickname;
        private boolean likeYn;
        private boolean unlikeYn;
    }

    // 질문 태그 생성 Dto
    @Getter
    public static class TagPost {
        private Long questionsTagsId;
    }

    // 질문 태그 응답 Dto
    @Builder
    @Getter
    @AllArgsConstructor
    public static class TagResponse {
        private Long tagId;
        private String title;
    }
}
