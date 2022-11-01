package com.pre21.repository;

import com.pre21.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersRepository extends JpaRepository<Answers, Long> {



    //Optional<Answers> countByQuestionId(Long questionId); 종속성 오류 발생
}
