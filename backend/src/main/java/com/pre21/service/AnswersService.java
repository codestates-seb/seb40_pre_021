package com.pre21.service;

import com.pre21.dto.AnswersDto;
import com.pre21.entity.Answers;
import com.pre21.entity.Questions;
import com.pre21.entity.User;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.AnswersRepository;
import com.pre21.repository.QuestionsRepository;
import com.pre21.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswersService {
    private final QuestionsRepository questionsRepository;
    private final UserRepository userRepository;
    private final AnswersRepository answersRepository;

    public void createAnswer(AnswersDto.Post answersPostDto, Long questionId) {
        // 질문 찾기
        Questions findQuestion = questionsRepository.findQuestionsById(questionId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        // 유저 찾기
        User findUser = userRepository.findUserByEmail(answersPostDto.getEmail()).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        // 답변 생성
        Answers answers = new Answers(answersPostDto.getContents());

        // 답변에 유저, 질문 정보 매핑 저장
        answers.addUser(findUser);
        answers.addQuestion(findQuestion);

        Answers savedAnswer = answersRepository.save(answers);

        updateAnswerCount(findQuestion);

        // 유저, 질문에 답변 정보 매핑 저장
        findUser.addAnswers(savedAnswer);
        findQuestion.addAnswer(savedAnswer);

    }

    public void updateAnswerCount(Questions questions) {
        int earnedAnswerCount = questions.getAnswerCount() + 1;
        questions.setAnswerCount(earnedAnswerCount);

        questionsRepository.save(questions);
    }
}
