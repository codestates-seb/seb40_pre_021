package com.pre21.dto;

import com.pre21.entity.Adoption;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class MyPageDto {

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionInfo implements Comparable<QuestionInfo> {
        private Long id;
        private String title;
        private String contents;
        private List<String> tags;
        private int vote;
        private boolean choosed;
        private int views;
        private LocalDateTime createdAt;
        private int answerCount;
        private String url;

        @Override
        public int compareTo(QuestionInfo o) {
            return (int) (o.id - this.id);
        }
    }


    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TagInfo implements Comparable<TagInfo> {
        private Long id;
        private String title;
        private long tagCount;

        @Override
        public int compareTo(TagInfo o) {
            return (int) (o.tagCount - this.tagCount);
        }
    }


    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerInfo {
        private Long questionId;
        private Long id;
        private String title;
        private LocalDateTime createdAt;
        private Adoption adoption;
        private int vote;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerInfos implements Comparable<AnswerInfos> {
        private Long questionId;
        private Long id;
        private String title;
        private LocalDateTime createdAt;
        private boolean choosed;
        private int vote;
        private List<String> tags;
        private String url;

        @Override
        public int compareTo(AnswerInfos o) {
            return (int) (o.id - this.id);
        }
    }



    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookmarkInfo implements Comparable<BookmarkInfo> {
        private Long id;
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
        private BookmarkAnswer answer;

        @Override
        public int compareTo(BookmarkInfo o) {
            return (int) (o.id - this.id);
        }
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookmarkAnswer {
        private Long answerId;
        private String answerUser;
        private String answerBody;
        private LocalDateTime answerCreatedAt;
        private int vote;
        private boolean choosed;
    }
}
