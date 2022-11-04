package com.pre21.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 채택 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Adoption {
    // 채택 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADOPTION_ID")
    private Long id;

    // 채택 - 답변 1:1 매핑
    @OneToOne
    @JoinColumn(name = "ANSWER_ID")
    Answers answers = new Answers();

    // 채택 - 유저 1:1 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    User users = new User();

    // 채택 - 답변 1:1 매핑
    @OneToOne
    @JoinColumn(name = "QUESTION_ID")
    Questions questions = new Questions();


    // Answer 추가 메서드
    public void setAnswers(Answers answers) {
        this.answers = answers;
        if (answers.getAdoption() != this) {
            answers.setAdopted(this);
        }
    }

    // Question 추가 메서드
    public void setQuestions(Questions questions) {
        this.questions = questions;
        if (questions.getAdoption() != this) {
            questions.setAdopted(this);
        }
    }

    // User 추가 메서드
    public void setUsers(User user) {
        // 기존에 getUser 와 연관관계가 있다면
        // getUsers 에서 해당 Adoption 을 삭제
        if(this.getUsers() != null) {
            this.users.getAdoptions().remove(this);
        }
        this.users = user;
        // 무한 루프 방지
        if(user.getAdoptions() != this) {
            user.addAdoption(this);
        }
    }
}
