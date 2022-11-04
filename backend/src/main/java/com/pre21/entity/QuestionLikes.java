package com.pre21.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 질문 좋아요 엔티티
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class QuestionLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKES_ID")
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "QUESTION_ID")
    private Questions questions = new Questions();

    @ManyToOne (fetch =  FetchType.LAZY)
    @JoinColumn (name = "USER_ID")
    private User users = new User();

    @Column
    private boolean likeYn = false;

    @Column
    private boolean unlikeYn = false;


    public void addQuestions(Questions questions) {
        // 기존에 getQuestionsTags 와 연관관계가 있다면
        // getQuestionsTags 에서 해당 questions 을 삭제
        if(this.questions != null) {
            this.questions.getQuestionsLikes().remove(this);
        }
        this.questions = questions;
        // 무한 루프 방지
        if(questions.getQuestionsLikes() != this) {
            questions.addQuestionsLikes(this);
        }
    }
    public void setUsers(User user) {
        // 기존에 getQuestionsLikes 와 연관관계가 있다면
        // getQuestionsLikes 에서 해당 user 을 삭제
        if(this.users != null) {
            this.users.getQuestionsLikes().remove(this);
        }
        this.users = user;
        // 무한 루프 방지
        if(users.getQuestionsLikes() != this) {
            users.addQuestionsLikes(this);
        }
    }

    @Builder
    public QuestionLikes(boolean likeYn, boolean unlikeYn) {
        this.likeYn = likeYn;
        this.unlikeYn = unlikeYn;
    }
}
