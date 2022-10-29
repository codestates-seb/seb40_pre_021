package com.pre21.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tags {
    @Id
    @Column(name = "TAG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "TAG_TITLE")
    private String title;

    @Column(nullable = false, name = "TAG_COUNT")
    private int count;

    @Column
    private LocalDateTime latest = LocalDateTime.now();

    @OneToMany(mappedBy = "tags")
    private List<QuestionsTags> questionsTags = new ArrayList<>();

    @OneToMany(mappedBy = "tags")
    private List<UserTags> userTags = new ArrayList<>();

    public Tags(String title) {
        this.title = title;
        this.count = 1; //질문 생성 시 같이 생성되는 태그는 초기값 1 세팅
    }

    public void addQuestionTags(QuestionsTags questionsTags) {
        this.questionsTags.add(questionsTags); // question 에 questionsTags 지정
        if (questionsTags.getTags() != this) {
            questionsTags.setTags(this); //(owner)questionsTags 에 question 지정
        }
    }

    public void addUserTags(UserTags userTags) {
        this.userTags.add(userTags);
        if(userTags.getTags() != this) {
            userTags.setTags(this);
        }
    }
}
