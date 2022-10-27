package com.pre21.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// Questions 테이블과 Tags 테이블 N:N 관계를 1:N:1로 만들기 위한 Entity
@Entity
@Getter
@Setter
public class QuestionsTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionsTagsId;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Questions questions;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tags tags;


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
        if(this.tags != null) {
            this.tags.getQuestionsTags().remove(this);
        }
        this.tags = tags;
        if(tags.getQuestionsTags() != this) {
            tags.addQuestionTags(this);
        }
    }
}
