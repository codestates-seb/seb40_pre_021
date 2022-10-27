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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tag_id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String comments;

    @Column(nullable = false)
    private Integer tag_count;

    @Column(nullable = false)
    private LocalDateTime latest = LocalDateTime.now();

    @OneToMany(mappedBy = "tags", cascade = CascadeType.PERSIST)
    private List<QuestionsTags> questionsTags = new ArrayList<>();

    @OneToMany(mappedBy = "tags", cascade = CascadeType.PERSIST)
    private List<UserTags> userTags = new ArrayList<>();

    public void addQuestionTags(QuestionsTags questionsTags) {
        this.questionsTags.add(questionsTags); // question 에 questionsTags 지정
        if (questionsTags.getTags() != this) {
            questionsTags.setTags(this); //(owner)questionsTags 에 question 지정
        }
    }

    public void addUserTags(UserTags userTags) {
        this.userTags.add(userTags); // tag 에 userTags 지정
        if(userTags.getTags() != this) {
            userTags.setTags(this); //(owner) userTags 에 tag 지정
        }
    }
}
