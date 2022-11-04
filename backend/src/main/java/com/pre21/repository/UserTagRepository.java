package com.pre21.repository;

import com.pre21.entity.Tags;
import com.pre21.entity.User;
import com.pre21.entity.UserTags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTagRepository extends JpaRepository<UserTags, Long> {
    Optional<UserTags> findByTagsAndUsers(Tags tags, User users);
}
