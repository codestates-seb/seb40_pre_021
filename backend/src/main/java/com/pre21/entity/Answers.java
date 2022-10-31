package com.pre21.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;

    @Column
    private String contents;

    @Column
    private int likesCount;

    @Column
    private int unlikesCount;

    @Column
    private boolean chooseYN;

    @Column
    private String imageUrl;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    // 딥변 - 질문 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private Questions questions = new Questions();

    // 답변 - 유저 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User users = new User();

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
}
