package com.pre21.controller;

import com.pre21.dto.AnswerCommentPostDto;
import com.pre21.dto.QuestionCommentPostDto;
import com.pre21.mapper.QuestionCommentMapper;
import com.pre21.service.AnswerCommentService;
import com.pre21.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CommentController {
    private final QuestionCommentService questionCommentService;
    private final AnswerCommentService answerCommentService;
    private final QuestionCommentMapper mapper;

    /**
     * 질문에 대한 댓글 생성 <br>
     * POST "/questions/{question-id}/comment" <br>
     * requestBody : {"userId":"userId", "comments":"string"} <br>
     * responseBody : NULL
     *
     * @param questionId             댓글을 생성하는 질문의 Id입니다.
     * @param questionCommentPostDto 댓글을 생성하는 요청의 RequestBody에 해당합니다.
     * @author dev32user
     */
    @PostMapping("/questions/{question-id}/comment")
    public void createQuestionComment(
            @PathVariable("question-id") Long questionId,
            @RequestBody QuestionCommentPostDto questionCommentPostDto) throws Exception {

        questionCommentService.createQuestionComment(questionCommentPostDto, questionId);
    }

    /**
     * 답변에 대한 댓글 생성입니다. <br>
     * POST "/answers/{answer-id}/comment"<br>
     * requestBody : {"userId":"userId", "comments":"string"} <br>
     * responseBody : NULL
     *
     * @param answerId             댓글을 생성하는 답변의 Id입니다.
     * @param answerCommentPostDto 댓글을 생성하는 요청의 RequestBody에 해당합니다.
     * @author dev32user
     */
    @PostMapping("/answers/{answer-id}/comment")
    public void createAnswerComment(
            @PathVariable("answer-id") Long answerId,
            @RequestBody AnswerCommentPostDto answerCommentPostDto) throws Exception {

        answerCommentService.createAnswerComment(answerCommentPostDto, answerId);
    }
}
