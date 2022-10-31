package com.pre21.repository;

import com.pre21.entity.QuestionLikes;
import com.pre21.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionLikeRepository extends JpaRepository<QuestionLikes, Long> {
    Optional<QuestionLikes> findQuestionLikesByUsers(User user);
}
