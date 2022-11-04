package com.pre21.service;

import com.pre21.dto.AnswersDto;
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

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswersService {
    private final QuestionsRepository questionsRepository;
    private final AuthRepository authRepository;
    private final AnswersRepository answersRepository;
    private final AnswerCommentRepository answerCommentRepository;

    public void createAnswer(AnswersDto.Post answersPostDto, Long questionId) {
        // 질문 찾기
        Questions findQuestion = questionsRepository.findQuestionsById(questionId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        // 유저 찾기
        User findUser = authRepository.findUserByEmail(answersPostDto.getEmail()).orElseThrow(()
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
    public Answers patchAnswer(Long userId, Long answerId, AnswersDto.Patch answerPatchDto) {
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
     * 답변 delete 요청에 대한 서비스 메서드입니다.
     *
     * @param userId   Long 타입 사용자 Id 값입니다.
     * @param answerId Long 타입 삭제 할 답변의 Id 값입니다.
     * @author dev32user
     */
    public void deleteAnswer(Long userId, Long answerId) {
        userIdAnswerIdCheck(userId, answerId);
        Answers findAnswer = findVerifiedAnswer(answerId);

        answersRepository.delete(findAnswer);
    }

    /**
     * userId와 answer 객체에 저장된 사용자의 Id 값이 일치하는지 확인하는 메서드입니다.
     * 일치하지 않으면 ExceptionCode.UNAUTHORIZED_USER 코드를 반환합니다.
     *
     * @param userId   검사에 사용될 사용자의 Id 값입니다.
     * @param answerId 검사에 사용될 답변의 Id 값입니다. 해당 값을 바탕으로 답변을 작성한 사용자의 ID를 조회합니다.
     * @author dev32user
     */
    private void userIdAnswerIdCheck(Long userId, Long answerId) {
        if (!Objects.equals(userId, verfiedAnswer(answerId).getUsers().getId())) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);
        }
    }

    /**
     * Id를 입력 받아서 answersRepository에서 Answers 객체를 찾아 반환하는 메서드입니다.
     * 객체를 찾지 못하면 ExceptionCode.ANSWER_NOT_FOUND 코드를 반환합니다.
     *
     * @param answerId Long 타입 찾을 답변의 Id 값입니다.
     * @return Answers
     * @author dev32user
     */
    public Answers findVerifiedAnswer(Long answerId) {
        Optional<Answers> optionalAnswer = answersRepository.findById(answerId);

        return optionalAnswer.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }


    /**
     * 답변에 대한 댓글을 생성하는 메서드입니다.
     * AnswerCommentRepository에 입력받은 answerCommentPostDto를 저장합니다.
     *
     * @param answerCommentPostDto 댓글을 생성하는 요청의 RequestBody에 해당합니다.
     * @param userId               댓글 작성하는 유저의 Id 값입니다.
     * @param answerId             댓글을 생성하는 답변의 Id 값입니다.
     * @author dev32user
     */
    public void createAnswerComment(AnswersDto.CommentPost answerCommentPostDto, Long userId, Long answerId) throws Exception {
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


    public Page<Answers> findMyAnswers(Long userId, int page, int size) {
        return answersRepository.findAllByUsersId(
                userId,
                PageRequest.of(page, size, Sort.by("id").descending()));
    }
}
