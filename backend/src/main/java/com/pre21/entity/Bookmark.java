package com.pre21.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Bookmark {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmark_id;

    @Column
    private Long user_id;

    @Column
    private Long question_id;

    @Column
    private Long answer_id;
}
