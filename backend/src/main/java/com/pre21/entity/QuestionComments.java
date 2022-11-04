package com.pre21.entity;


import com.pre21.util.auditable.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 질문에 대한 댓글 Entity입니다.
 *
 * @author dev32user
 */
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

    @Column
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Questions questions;

    // TODO : User.java 구현 시 추가
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    /**
     * 댓글의 내용을 받아서 QuestionComments 엔티티에 저장합니다.
     *
     * @param comments 댓글의 내용을 String 타입으로 받습니다.
     * @author dev32user
     */
    public QuestionComments(String comments) {
        this.comments = comments;
    }

    // TODO : User 클래스 주입 메서드 구현 요
}
