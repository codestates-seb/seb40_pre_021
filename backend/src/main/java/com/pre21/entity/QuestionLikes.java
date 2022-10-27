package com.pre21.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class QuestionLikes {

    @Id
    @Column(nullable = false)
    private Long question_id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private boolean like_Yn = false;

    @Column(nullable = false)
    private boolean Unlike_Yn = false;

}
