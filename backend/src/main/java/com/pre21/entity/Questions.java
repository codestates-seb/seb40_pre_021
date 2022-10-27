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

    @OneToMany(mappedBy = "questions", cascade = CascadeType.PERSIST)
    private List<QuestionsTags> questionsTags = new ArrayList<>();

    @OneToMany(mappedBy = "questions", cascade = CascadeType.PERSIST)
    private List<QuestionComments> comments = new ArrayList<>();

    public Questions(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.answerYn = false;
    }

    public void addQuestionsTags(QuestionsTags questionsTags) {
        this.questionsTags.add(questionsTags); // question 에 questionsTags 지정
        if (questionsTags.getQuestions() != this) {
            questionsTags.setQuestions(this); //(owner)questionsTags 에 question 지정
        }
    }

    public void addQuestionComments(QuestionComments questionComments) {
        this.comments.add(questionComments);
        if (questionComments.getQuestions() != this) {
            questionComments.addQuestion(this);
        }
    }
}
