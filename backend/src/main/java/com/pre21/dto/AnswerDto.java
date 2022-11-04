package com.pre21.dto;

import com.pre21.entity.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


public class AnswerDto {
    // 답변 생성 POST
    @Getter
    @AllArgsConstructor
    public static class Post {
        // 유저 이메일
        private String email;
        // 답변 내용
        private String contents;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private String contents;
    }

    // 질문 조회 시 생성되어 있는 답변 조회
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private Long answerId;
        private String contents;
        private int vote;
        private boolean chooseYn;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private String nickname;
        private List<CommentResponse> comments;
        private List<BookmarkResponse> bookmarks;
        private List<LikeResponse> answerLikes;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetResponse {
        private Long answerId;
        private String contents;
        private int vote;
        private Adoption adoption;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private User user;
        private List<AnswerLikes> answerLikes;
        private Questions questions;
    }




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


    /**
     * AnswerComment 생성 Post 요청 시 Dto입니다. <br>
     * RequestBody에 해당합니다.
     *
     * @author dev32user
     */
    @Getter
    @Setter
    @AllArgsConstructor
    public static class CommentPost {
        private Long userId;
        private String comments;
    }


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponse {
        private Long commentId;
        private String comments;
        private LocalDateTime createdAt;
        private String nickname;
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookmarkResponse {
        private Long bookmarkId;
        private Long userId;
        private String nickname;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class AnswerInfo {
        private Long questionId;
        private Long id;
        private Long title;
        private LocalDateTime createdAt;
        private Adoption adoption;
        private Long vote;
    }
}
