package com.pre21.service;

import com.pre21.dto.AnswerCommentPostDto;
import com.pre21.entity.Answers;
import com.pre21.entity.User;
import com.pre21.repository.AnswerCommentRepository;
import com.pre21.repository.AnswersRepository;
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
    private final AnswersRepository answersRepository;
    private final UserRepository userRepository;
    private final AnswerCommentRepository answerCommentRepository;

    /**
     * 답변에 대한 댓글을 생성하는 메서드입니다.
     * AnswerCommentRepository에 입력받은 answerCommentPostDto를 저장합니다.
     *
     * @param answerCommentPostDto 댓글을 생성하는 요청의 RequestBody에 해당합니다.
     * @param answerId             댓글을 생성하는 답변의 Id입니다.
     * @author dev32user
     */
    public void createAnswerComment(AnswerCommentPostDto answerCommentPostDto, Long answerId) {
        Long userId = answerCommentPostDto.getUserId();
        User findUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("findUser.findById 실패"));
        Answers answers = answersRepository.findAnswerById(answerId).
    }
}
