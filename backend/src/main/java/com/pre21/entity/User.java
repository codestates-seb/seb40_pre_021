package com.pre21.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pre21.util.auditable.Auditable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 유저 엔티티
 */
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USERS")
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String nickname;

    // Security 유저 권한
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    //
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Questions> questions = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<QuestionLikes> questionsLikes = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<UserTags> userTags = new ArrayList<>();

    // 유저 - 답변 매핑
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Answers> answers = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<AnswerLikes> answerLikes = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Adoption> adoptions = new ArrayList<>();


    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();


    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
//        this.roles.add("USER");
    }

    public void addQuestion(Questions question) {
        this.questions.add(question);
    }


    public User(Long id, String email, List<String> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public void addQuestionsLikes(QuestionLikes questionLikes) {
        this.questionsLikes.add(questionLikes); // user 에 questionLikes 지정
        if (questionLikes.getUsers() != this) {
            questionLikes.setUsers(this); //(owner)questionLikes 에 user 지정
        }
    }

    public void addUserTags(UserTags userTags) {
        this.userTags.add(userTags);
        if(userTags.getUsers() != this) {
            userTags.setUser(this);
        }
    }

    public void addAnswers(Answers answers) {
        this.answers.add(answers);
    }

    public void addAnswerLike(AnswerLikes answerLikes) {
        this.answerLikes.add(answerLikes);
        if (answerLikes.getUsers() != this) {
            answerLikes.setUsers(this);
        }
    }


    // Adoption 리스트 추가 메서드
    public void addAdoption(Adoption adoption) {
        this.adoptions.add(adoption);
        if (adoption.getUsers() != this) {
            adoption.setUsers(this);
        }
    }

    /**
     * @method
     * @param bookmark
     */
    public void addAdoption(Bookmark bookmark) {
        this.bookmarks.add(bookmark);
        if (bookmark.getUsers() != this) {
            bookmark.setUsers(this);
        }
    }
}
