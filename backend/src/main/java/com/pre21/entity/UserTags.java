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
    private Long userTagsId;

/*
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
*/

/*
    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tags tags;
*/

/*    public void setUser(User user) {
        if(this.user != null) {
            this.user.getUserTags().remove(this);
        }
        this.user = user;
        if(user.getUserTags() != this) {
            user.addUserTags(this);
        }
    }*/

/*    public void setTags(Tags tags) {
        if(this.tags != null) {
            this.tags.getUserTags().remove(this);
        }
        this.tags = tags;
        if(tags.getUserTags() != this) {
            tags.addUserTags(this);
        }
    }*/
}
