package com.pre21.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static com.pre21.util.RestConstants.BOOKMARK_URL;

/**
 * 북마크 엔티티
 */
@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class Bookmark {
    @Id
    @Column(name = "BOOKMARK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User users;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Questions questions;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answers answers;

    @Column(length = 1000)
    private String url;

    @Column
    private LocalDateTime createdAt;

    public Bookmark(Long questionId) {
        this.url = BOOKMARK_URL + questionId.toString();
        this.createdAt = LocalDateTime.now();
    }
}
