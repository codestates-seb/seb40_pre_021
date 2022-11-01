package com.pre21.entity;

import com.pre21.util.auditable.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 * 오류 : 클래스 'AnswerComments'에는 [public, protected] no-arg 생성자가 포함되어야 합니다.
 * 를 고치기 위해서 수정했습니다. <br>
 * Answers.java 파일 또한 마찬가지로 수정하였습니다.
 */

/**
 * 답변에 대한 댓글 Entity입니다.
 *
 * @author dev32user
 */
@Entity
@Getter
@Setter
@Table(name = "ANSWER_COMMENTS")
@NoArgsConstructor
public class AnswerComments extends Auditable {
    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answers answers;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    /**
     * 댓글의 내용을 받아서 AnswerComments 엔티티에 저장합니다.
     *
     * @param comments 댓글의 내용을 String 타입으로 받습니다.
     * @author dev32user
     */
    public AnswerComments(String comments) {
        this.comments = comments;
    }
}
