package com.pre21.repository;

import com.pre21.entity.Questions;
import com.pre21.entity.QuestionsTags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionsTagsRepository extends JpaRepository<QuestionsTags, Long> {

    //Optional<QuestionsTags> findTagsByQuestion(Questions questions);
}
