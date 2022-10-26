package com.pre21.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 오류 : 클래스 'AnswerComments'에는 [public, protected] no-arg 생성자가 포함되어야 합니다.
 * 를 고치기 위해서 수정했습니다.
 * Answers.java 파일 또한 마찬가지로 수정하였습니다.
 */
@Table
@Entity
@Getter
@Setter
public class AnswerComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

}
