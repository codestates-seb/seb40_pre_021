package com.pre21.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static com.pre21.util.RestConstants.BOOKMARK_BASIC_PAGE;

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

    private String url;

    public Bookmark(Long questionId) {
        this.url = BOOKMARK_BASIC_PAGE + questionId.toString();
    }

    public Bookmark(Long questionId, Long answerId) {
        this.url = BOOKMARK_BASIC_PAGE + questionId.toString();
    }
}
