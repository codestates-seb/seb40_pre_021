package com.pre21.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Questions 테이블과 Tags 테이블 N:N 관계를 1:N:1 관계로 만들기 위한 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class QuestionsTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTIONS_TAGS_ID")
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Questions questions;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tags tags;

    @Column
    private String tagValue;


    public void setQuestions(Questions questions) {
        // 기존에 getQuestionsTags 와 연관관계가 있다면
        // getQuestionsTags 에서 해당 questions 을 삭제
        if(this.questions != null) {
            this.questions.getQuestionsTags().remove(this);
        }
        this.questions = questions;
        // 무한 루프 방지
        if(questions.getQuestionsTags() != this) {
            questions.addQuestionsTags(this);
        }
    }

    public void setTags(Tags tags) {
        // 기존에 getQuestionsTags 와 연관관계가 있다면
        // getQuestionsTags 에서 해당 tags 을 삭제
        if(this.tags != null) {
            this.tags.getQuestionsTags().remove(this);
        }
        // 무한 루프 방지
        this.tags = tags;
        if(tags.getQuestionsTags() != this) {
            tags.addQuestionTags(this);
        }
    }

    public QuestionsTags(Questions questions,String tagValue ,Tags tags) {
        this.questions = questions;
        this.tagValue = tagValue;
        this.tags = tags;
    }
}
