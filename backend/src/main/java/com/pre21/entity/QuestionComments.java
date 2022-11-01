package com.pre21.entity;


import com.pre21.util.auditable.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "QUESTION_COMMENTS")
@NoArgsConstructor
public class QuestionComments extends Auditable {
    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String comments;

    @Column
    private final LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Questions questions;

    // TODO : User.java 구현 시 추가
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public QuestionComments(String comments) {
        this.comments = comments;
    }

    // TODO : User 클래스 주입 메서드 구현 요
}
