package com.pre21.service;

import com.pre21.dto.QuestionCommentPostDto;
import com.pre21.entity.QuestionComments;
import com.pre21.entity.Questions;
import com.pre21.entity.User;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.QuestionCommentRepository;
import com.pre21.repository.QuestionsRepository;
import com.pre21.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author dev32user
 */
@Service
@RequiredArgsConstructor
public class QuestionCommentService {
    private final QuestionsRepository questionsRepository;
    private final UserRepository userRepository;
    private final QuestionCommentRepository questionCommentRepository;

    /**
     * 질문에 대한 댓글을 생성하는 메서드입니다. QuestionCommentRepository에 입력받은 questionCommentPostDto를 저장합니다.
     *
     * @param questionCommentPostDto 댓글을 생성하는 요청의 RequestBody에 해당합니다.
     * @param questionId             댓글을 생성하는 질문의 Id입니다.
     * @author dev32user
     */
    public void createQuestionComment(QuestionCommentPostDto questionCommentPostDto, Long questionId) throws Exception {
        Long userId = questionCommentPostDto.getUserId();
        User findUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("d"));
        Questions questions = questionsRepository.findQuestionsById(questionId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        QuestionComments questionComments = new QuestionComments(questionCommentPostDto.getComments());
        questionComments.setQuestions(questions);
        questionComments.setUser(findUser);
        questionCommentRepository.save(questionComments);
    }
}
