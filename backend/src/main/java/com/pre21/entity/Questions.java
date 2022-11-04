package com.pre21.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pre21.util.auditable.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 질문 엔티티
 */
@Entity
@Getter
@Setter
@Table(name = "QUESTIONS")
@NoArgsConstructor
public class Questions {
    // 질문 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID")
    private Long id;

    // 질문 제목
    @Column
    private String title;

    // 질문 내용
    @Column
    private String contents;

    // 질문 채택 여부
    @Column
    private boolean chooseYn = false;

    // 질문 조회수
    @Column
    private int views;

    // 질문 좋아요,싫어요 계산
    @Column
    private int vote;

    // 질문 생성 일자
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    // 질문 수정 일자
    @Column
    private LocalDateTime modifiedAt = LocalDateTime.now();

    // 질문에 달린 답변 개수
    @Column
    int answerCount = 0;

    // 질문 - 답변 1:1 매핑
    @OneToOne(mappedBy = "questions", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Adoption adoption;

    // 질문 - 질문태그 1:N 매핑
    @JsonIgnore
    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<QuestionsTags> questionsTags = new ArrayList<>();

    // 질문 - 질문댓글 1:N 매핑
    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<QuestionComments> comments = new ArrayList<>();

    // 질문 - 유저 N:1 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User users = new User();

    // 질문 : 질문좋아요 1:N 매핑
    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<QuestionLikes> questionsLikes = new ArrayList<>();

    // 질문 - 답변 1:N 매핑
    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<Answers> answers = new ArrayList<>();

    // 질문 - 북마크 1:N 매핑
    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    // 질문 생성 시 사용 생성자
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
        this.questionsLikes.add(questionLikes); // question 에 questionsLikes 지정
        if (questionLikes.getQuestions() != this) {
            questionLikes.addQuestions(this); //(owner)questionsLikes 에 question 지정
        }
    }

    public void addAnswer(Answers answers) {
        this.answers.add(answers);
    }

    public void setAdopted(Adoption adoption) {
        this.adoption = adoption;
        if (adoption.getQuestions() != this) {
            adoption.setQuestions(this);
        }
    }
}
