package com.pre21.repository;

import com.pre21.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersRepository extends JpaRepository<Answers, Long> {

    /**
     * answerId를 입력받아서 Answers 엔티티를 찾습니다.
     *
     * @param answerId      찾을 Answers entity의 Id입니다.
     * @author dev32user
     */
    Optional<Answers> findAnswerById(Long answerId);


    //Optional<Answers> countByQuestionId(Long questionId); 종속성 오류 발생
}
