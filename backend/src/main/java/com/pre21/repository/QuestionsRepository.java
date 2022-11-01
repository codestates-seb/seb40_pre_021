package com.pre21.repository;

import com.pre21.entity.Questions;
import com.pre21.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    Optional<Questions> findQuestionsByUsers(User user);

    /**
     * questionId를 입력받아서 Answers 엔티티를 찾습니다.
     *
     * @param questionId      찾을 Question entity의 Id입니다.
     * @author dev32user
     */
    Optional<Questions> findQuestionsById(Long questionId);

}
