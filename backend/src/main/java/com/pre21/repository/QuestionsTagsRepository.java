package com.pre21.repository;

import com.pre21.entity.Questions;
import com.pre21.entity.QuestionsTags;
import com.pre21.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionsTagsRepository extends JpaRepository<QuestionsTags, Long> {

    void deleteAllByQuestions(Questions questions);
    //Optional<QuestionsTags> findTagsByQuestion(Questions questions);

    @Query(value = "SELECT qt FROM QuestionsTags qt WHERE qt.tagValue = ?1")
    List<QuestionsTags> findQtag(String tagvalue);

}
