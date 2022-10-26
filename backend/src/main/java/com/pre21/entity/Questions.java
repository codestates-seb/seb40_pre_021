package com.pre21.entity;

import com.pre21.util.auditable.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "QUESTIONS")
@NoArgsConstructor
public class Questions extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false, name = "ANSWER_YN")
    private boolean answerYn;

    @Column
    private int views;

    @Column(name = "LIKES_COUNT")
    private int like;

    @Column(name = "UNLIKES_COUNT")
    private int unlike;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    // TODO : Tags 자바가 기입되면 추가
//    @OneToMany(mappedBy = "questions", cascade = CascadeType.PERSIST)
//    private List<Tags> tags = new ArrayList<>();

    @OneToMany(mappedBy = "questions", cascade = CascadeType.PERSIST)
    private List<QuestionComments> comments = new ArrayList<>();

    public Questions(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.answerYn = false;
    }

    public void addQuestionComments(QuestionComments questionComments) {
        this.comments.add(questionComments);
        if (questionComments.getQuestions() != this) {
            questionComments.addQuestion(this);
        }
    }

    // TODO : 태그 리스트 태그 추가 메서드 구현 요
}
