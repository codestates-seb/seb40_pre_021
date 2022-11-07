package com.pre21.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 답변 좋아요 엔티티
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class AnswerLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKES_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANSWER_ID")
    private Answers answers = new Answers();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User users = new User();

    @Column
    private boolean likeYn = false;

    @Column
    private boolean unlikeYn = false;


    public void addAnswer(Answers answer) {
        // 기존에 getAnswersLike 와 연관관계가 있다면
        // getAnswersLike 에서 해당 answer 을 삭제
        if(this.answers != null) {
            this.answers.getAnswersLike().remove(this);
        }
        this.answers = answer;
        // 무한 루프 방지
        if(answer.getAnswersLike() != this) {
            answer.addAnswerLike(this);
        }
    }
    public void setUsers(User user) {
        // 기존에 getAnswerLikes 와 연관관계가 있다면
        // getAnswerLikes 에서 해당 user 을 삭제
        if(this.users != null) {
            this.users.getAnswerLikes().remove(this);
        }
        this.users = user;
        // 무한 루프 방지
        if(users.getAnswerLikes() != this) {
            users.addAnswerLike(this);
        }
    }

    @Builder
    public AnswerLikes(boolean likeYn, boolean unlikeYn) {
        this.likeYn = likeYn;
        this.unlikeYn = unlikeYn;
    }
}
