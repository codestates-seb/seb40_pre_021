package com.pre21.service;

import com.pre21.dto.AnswerPatchDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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

    /**
     * answerId를 받아서 저장소에서 Answers 객체를 찾아 반환하는 private 메서드입니다.
     *
     * @param answerId Long 타입 answerId를 받습니다.
     * @return Answers
     * @author dev32user
     */
    private Answers verfiedAnswer(Long answerId) {
        return answersRepository
                .findById(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }

    /**
     * 답변 patch 요청에 대한 서비스 메서드입니다.
     *
     * @param userId         Long 타입 사용자 Id 값입니다.
     * @param answerId       Long 타입 answer Id 값입니다.
     * @param answerPatchDto AnswerPatchDto 요청입니다.
     * @author dev32user
     */
    public Answers patchAnswer(Long userId, Long answerId, AnswerPatchDto answerPatchDto) {
        if (!Objects.equals(userId, verfiedAnswer(answerId).getUsers().getId())) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);
        }

        Optional<Answers> optionalAnswer = answersRepository.findById(answerId);
        User findUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        Answers updatedAnswer =
                optionalAnswer
                        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        updatedAnswer.setContents(answerPatchDto.getConents());
        return answersRepository.save(updatedAnswer);
    }

    /**
     * 페이지네이션을 위해 Answer 객체들을 정해진 페이지, 크기에 따라 전부 찾아서 Page 형태로 반환합니다.
     *
     * @param page  int 타입의 페이지 값입니다.
     * @param size  int 타입의 크기 값입니다.
     * @return Page
     * @author dev32user
     */
    public Page<Answers> findPageAnswers(int page, int size) {
        return answersRepository
                .findAll(PageRequest
                        .of(page, size, Sort.by("id").descending()));
    }

    public Page<Answers> findMyAnswers(Long userId, int page, int size) {
        return answersRepository.findAllByUsersId(
                        userId,
                PageRequest.of(page, size, Sort.by("id").descending()));
    }
}
