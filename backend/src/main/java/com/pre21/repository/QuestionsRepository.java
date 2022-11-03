package com.pre21.repository;

import com.pre21.entity.Questions;
import com.pre21.entity.QuestionsTags;
import com.pre21.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    Optional<Questions> findQuestionsByUsers(User user);

    Optional<Questions> findQuestionsById(Long questionId);

    Page<Questions> findAllByUsersId(Long userId, PageRequest id);

    List<Questions> findAllByQuestionsTagsOrderByChooseYnAsc(List<QuestionsTags> questionsTags);

    @Query(value = "SELECT Questions FROM Questions WHERE title LIKE ?1 ORDER BY chooseYn ASC")
    List<Questions> findByKeyword(String keyword);
}
