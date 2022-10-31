package com.pre21.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
}
