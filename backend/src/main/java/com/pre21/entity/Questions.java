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

    private String url;

    @Column
    private String contents;

    @Column(name = "ANSWER_YN")
    private boolean choosed;

    @Column
    private int views;

    @Column(name = "VOTE")
    private int vote;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;

    private Long qesId;

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<QuestionsTags> questionsTags = new ArrayList<>();


    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<QuestionComments> comments = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User users = new User();



    public Questions(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.choosed = false;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
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
}
