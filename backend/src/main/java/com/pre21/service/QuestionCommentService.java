package com.pre21.service;

import com.pre21.dto.QuestionCommentPostDto;
import com.pre21.entity.Questions;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.QuestionCommentRepository;
import com.pre21.repository.QuestionsRepository;
import com.pre21.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionCommentService {
    private final QuestionsRepository questionsRepository;
    private final UserRepository userRepository;
    private final QuestionCommentRepository commentRepository;

    /**
     * Comment를 생성하는 메서드
     */
    public void createQuestionComment(QuestionCommentPostDto questionCommentPostDto, Long questionId) throws Exception{
        Long userId = questionCommentPostDto.getQuestion().getId();

    }
}
