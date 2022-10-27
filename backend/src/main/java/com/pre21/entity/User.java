package com.pre21.entity;

import com.pre21.util.auditable.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * JavaDocs 테스트
 * User entity입니다.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USERS")
public class User extends Auditable {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    private List<Answers> answers = new ArrayList<>();


    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
