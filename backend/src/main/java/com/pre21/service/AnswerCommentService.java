package com.pre21.service;

import com.pre21.dto.AnswerCommentPostDto;
import com.pre21.repository.AnswerCommentRepository;
import com.pre21.repository.QuestionsRepository;
import com.pre21.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author dev32user
 */
@Service
@RequiredArgsConstructor
public class AnswerCommentService {
    private final QuestionsRepository questionsRepository;
    private final UserRepository userRepository;
    private final AnswerCommentRepository answerCommentRepository;

    public void createAnswerComment(AnswerCommentPostDto answerCommentPostDto, Long answerId) {
    }
}
