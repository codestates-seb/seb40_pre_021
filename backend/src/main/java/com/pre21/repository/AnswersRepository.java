package com.pre21.repository;

import com.pre21.entity.Answers;
import com.pre21.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswersRepository extends JpaRepository<Answers, Long> {

    //Optional<Answers> countByQuestionId(Long questionId); 종속성 오류 발생
}
