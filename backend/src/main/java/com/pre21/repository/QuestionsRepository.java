package com.pre21.repository;

import com.pre21.entity.Questions;
import com.pre21.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    Optional<Questions> findQuestionsByUsers(User user);

    Optional<Questions> findQuestionsById(Long questionId);

    Page<Questions> findAllByUsersId(Long userId, PageRequest id);
}
