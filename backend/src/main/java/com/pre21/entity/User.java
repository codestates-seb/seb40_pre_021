package com.pre21.entity;

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
 * JavaDocs 테스트
 * User entity입니다.
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

    @Column
    private String password;

    @Column
    private String nickname;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Questions> questions = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<QuestionLikes> questionsLikes = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<UserTags> userTags = new ArrayList<>();

    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public void addQuestion(Questions question) {
        this.questions.add(question);
    }

    @Builder
    public User(Long id, String email, List<String> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;


    }
    public void addQuestionsLikes(QuestionLikes questionLikes) {
        this.questionsLikes.add(questionLikes); // question 에 questionsTags 지정
        if (questionLikes.getUsers() != this) {
            questionLikes.setUsers(this); //(owner)questionsTags 에 question 지정
        }
    }

    public void addUserTags(UserTags userTags) {
        this.userTags.add(userTags);
        if(userTags.getUsers() != this) {
            userTags.setUser(this);
        }
    }
}
