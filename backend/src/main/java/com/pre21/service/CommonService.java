package com.pre21.service;

import com.pre21.entity.Answers;
import com.pre21.entity.Questions;
import com.pre21.entity.User;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.AnswersRepository;
import com.pre21.repository.AuthRepository;
import com.pre21.repository.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommonService {
    private final AuthRepository authRepository;
    private final QuestionsRepository questionsRepository;
    private final AnswersRepository answersRepository;

    /**
     * @method 유저 조회
     * @param userId 유저식별자
     * @return User
     * @author mozzi327
     */
    private User verifiedExistUser(Long userId) {
        return authRepository.findById(userId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)
        );
    }


    /**
     * @method 질문 조회
     * @param questionId 질문식별자
     * @return Questions
     * @author mozzi327
     */
    private Questions verifiedExistQuestion(Long questionId) {
        return questionsRepository.findById(questionId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND)
        );
    }


    /**
     * @method 답변 조회
     * @param answerId 답변식별자
     * @return Answers
     * @author mozzi327
     */
    private Answers verifiedExistAnswer(Long answerId) {
        return answersRepository.findById(answerId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND)
        );
    }
}
