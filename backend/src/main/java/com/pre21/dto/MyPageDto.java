package com.pre21.dto;

import com.pre21.entity.Adoption;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class MyPageDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class TagInfo {
        private Long id;
        private String title;
        private long tagCount;
    }


    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class AnswerInfo {
        private Long questionId;
        private Long id;
        private String title;
        private LocalDateTime createdAt;
        private Adoption choosed;
        private int vote;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerInfos {
        private Long questionId;
        private Long id;
        private String title;
        private LocalDateTime createdAt;
        private boolean choosed;
        private int vote;
    }



    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class BookmarkInfo {
        private Long questionId;
        private String questionUser;
        private String title;
        private String url;
        private List<String> tag;
        private int vote;
        private boolean choosed;
        private int views;
        private int answerCount;
        private LocalDateTime createdAt;
    }
}
