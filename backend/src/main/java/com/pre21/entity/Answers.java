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

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int likesCount;

    @Column(nullable = false)
    private int unlikesCount;

    @Column(nullable = false)
    private boolean chooseYN;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();
}
