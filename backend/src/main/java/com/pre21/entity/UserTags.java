package com.pre21.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * User 와 Tags 의 N:N 관계를 1:N:1 관계로 만들기 위한 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_Tags_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User users;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tags tags;

    @Column
    private int tagCount;

    public void setUser(User user) {
        if(this.users != null) {
            this.users.getUserTags().remove(this);
        }
        this.users = user;
        if(user.getUserTags() != this) {
            user.addUserTags(this);
        }
    }

    public void setTags(Tags tags) {
        if(this.tags != null) {
            this.tags.getUserTags().remove(this);
        }
        this.tags = tags;
        if(tags.getUserTags() != this) {
            tags.addUserTags(this);
        }
    }

    public UserTags(User users, Tags tags) {
        this.users = users;
        this.tags = tags;
    }
}
