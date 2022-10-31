package com.pre21.entity;

import com.pre21.util.auditable.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "QUESTIONS")
@NoArgsConstructor
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID")
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @Column(name = "CHOOSE_YN")
    private boolean chooseYn = false;

    @Column
    private int views;

    @Column
    private int vote;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<QuestionsTags> questionsTags = new ArrayList<>();

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<QuestionComments> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User users = new User();


    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<QuestionLikes> questionsLikes = new ArrayList<>();

    public Questions(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }


    public void addUser(User user) {
        this.users = user;
    }

    public void addQuestionsTags(QuestionsTags questionsTags) {
        this.questionsTags.add(questionsTags); // question 에 questionsTags 지정
        if (questionsTags.getQuestions() != this) {
            questionsTags.setQuestions(this); //(owner)questionsTags 에 question 지정
        }
    }

    public void addQuestionsLikes(QuestionLikes questionLikes) {
        this.questionsLikes.add(questionLikes); // question 에 questionsTags 지정
        if (questionLikes.getQuestions() != this) {
            questionLikes.addQuestions(this); //(owner)questionsTags 에 question 지정
        }
    }
}
