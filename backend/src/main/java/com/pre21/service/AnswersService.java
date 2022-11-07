package com.pre21.service;

import com.pre21.dto.AnswerDto;
import com.pre21.entity.AnswerComments;
import com.pre21.entity.Answers;
import com.pre21.entity.Questions;
import com.pre21.entity.User;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.AnswerCommentRepository;
import com.pre21.repository.AnswersRepository;
import com.pre21.repository.QuestionsRepository;
import com.pre21.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswersService {
    private final QuestionsRepository questionsRepository;
    private final AuthRepository authRepository;
    private final AnswersRepository answersRepository;
    private final AnswerCommentRepository answerCommentRepository;

    /**
     * 답변 생성 메서드
     *
     * @param answersPostDto 답변 요청 바디
     * @param questionId 질문 식별자
     * @param userId 사용자 식별자
     * @author LimJaeminZ
     */
    public void createAnswer(AnswerDto.Post answersPostDto, Long questionId, Long userId) {
        // 질문 찾기
        Questions findQuestion = questionsRepository.findQuestionsById(questionId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        // 유저 찾기
        User findUser = authRepository.findById(userId).orElseThrow(()
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


    /**
     * 답변 수를 업데이트하는 메서드(로직 분리)
     *
     * @param questions 질문 정보
     * @author LimJaeminZ
     */
    public void updateAnswerCount(Questions questions) {
        int earnedAnswerCount = questions.getAnswerCount() + 1;
        questions.setAnswerCount(earnedAnswerCount);

        questionsRepository.save(questions);
    }


    /**
     * 답변 조회 메서드
     *
     * @param answerId 답변 식별자
     * @return Answers
     * @author dev32user
     */
    private Answers verfiedAnswer(Long answerId) {
        return answersRepository
                .findById(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }

    /**
     * 답변 수정 메서드
     *
     * @param userId 사용자 식별자
     * @param answerId 답변 식별자
     * @param answerPatchDto 답변 수정 응답 바디
     * @author dev32user
     */
    public Answers patchAnswer(Long userId, Long answerId, AnswerDto.Patch answerPatchDto) {
        userIdAnswerIdCheck(userId, answerId);

        /*
        User findUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        */

        Answers updatedAnswer = findVerifiedAnswer(answerId);

        updatedAnswer.setContents(answerPatchDto.getContents());
        return answersRepository.save(updatedAnswer);
    }

    /**
     * 답변 삭제 메서드
     *
     * @param userId 사용자 식별자
     * @param answerId 답변 식별자
     * @author dev32user
     */
    public void deleteAnswer(Long userId, Long answerId) {
        userIdAnswerIdCheck(userId, answerId);
        Answers findAnswer = findVerifiedAnswer(answerId);

        answersRepository.delete(findAnswer);
    }

    /**
     * userId와 answer 객체에 저장된 사용자 식별자 값이 일치하는지 확인하는 메서드
     *
     * @param userId 사용자 식별자
     * @param answerId 답변 식별자
     * @author dev32user
     */
    private void userIdAnswerIdCheck(Long userId, Long answerId) {
        if (!Objects.equals(userId, verfiedAnswer(answerId).getUsers().getId())) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);
        }
    }

    /**
     * 답변 조회 메서드
     *
     * @param answerId 답변 식별자
     * @return Answers
     * @author dev32user
     */
    public Answers findVerifiedAnswer(Long answerId) {
        Optional<Answers> optionalAnswer = answersRepository.findById(answerId);

        return optionalAnswer.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }


    /**
     * 답변 댓글 생성 메서드
     *
     * @param answerCommentPostDto 답변 댓글 요청 바디
     * @param answerId 답변 식별자
     * @author dev32user
     */
    public void createAnswerComment(AnswerDto.CommentPost answerCommentPostDto, Long userId, Long answerId) throws Exception {
        User findUser = authRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("findUser.findById 실패"));
        Answers answers = answersRepository
                .findAnswerById(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        AnswerComments answerComments = new AnswerComments(answerCommentPostDto.getComments());
        answerComments.setAnswers(answers);
        answerComments.setUser(findUser);
        answerComments.setNickname(findUser.getNickname()); // 2022.11.02 댓글 작성 유저 닉네임 추가
        answerCommentRepository.save(answerComments);
    }


    /**
     * 답변 리스트(페이지네이션) 반환 메서드
     *
     * @param userId 사용자 식별자
     * @param page 페이지 갯수
     * @param size 게시글 수
     * @return 페이지 정보
     * @author dev32user
     */
    public Page<Answers> findMyAnswers(Long userId, int page, int size) {
        return answersRepository.findAllByUsersId(
                userId,
                PageRequest.of(page, size, Sort.by("id").descending()));
    }
}
