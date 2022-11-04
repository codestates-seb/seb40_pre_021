package com.pre21.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 답변 엔티티
 */
@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWER_ID")
    private Long id;

    @Column
    private String contents;

    @Column
    private int vote;

    @OneToOne(mappedBy = "answers", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Adoption adoption;

    @Column
    private boolean chooseYn = false;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    // 답변 - 질문 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private Questions questions = new Questions();

    // 답변 - 유저 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User users = new User();


    // 답변 - 추천수 매핑
    @OneToMany(mappedBy = "answers", cascade = CascadeType.ALL)
    private List<AnswerLikes> answersLike = new ArrayList<>();

    @OneToMany(mappedBy = "answers", cascade = CascadeType.ALL)
    private List<AnswerComments> comments = new ArrayList<>();

    @OneToMany(mappedBy = "answers", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    // 답변 생성 시 필요 생성자
    public Answers(String contents) {
        this.contents = contents;
    }

    // 답변 - 질문 매핑 메소드
    public void addQuestion(Questions questions) {
        this.questions = questions;
    }

    // 답변 - 유저 매핑 메소드
    public void addUser(User users) {
        this.users = users;
    }

    public void addAnswerLike(AnswerLikes answerLike) {
        this.answersLike.add(answerLike); // question 에 questionsTags 지정
        if (answerLike.getAnswers() != this) {
            answerLike.addAnswer(this); //(owner)questionsTags 에 question 지정
        }
    }

    // Adopted 추가 메서드
    public void setAdopted(Adoption adoption) {
        this.adoption = adoption;
        if (adoption.getAnswers() != this) {
            adoption.setAnswers(this);
        }
    }
}
