package com.pre21.controller;

import com.pre21.dto.QuestionCommentPostDto;
import com.pre21.mapper.QuestionCommentMapper;
import com.pre21.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionCommentController {
    private final QuestionCommentService questionCommentService;
    private final QuestionCommentMapper mapper;

    /**
     * 질문에 대한 댓글 생성 <p>
     * POST "/{question-id}/comment" <p>
     * requestBody : {"userId":"userId", "comments":"string"} <p>
     * responseBody : NULL
     *
     * @param questionId             댓글을 생성하는 질문의 Id입니다.
     * @param questionCommentPostDto 댓글을 생성하는 요청의 RequestBody에 해당합니다.
     * @author dev32user
     */
    @PostMapping("/{question-id}/comment")
    public void createQuestionComment(
            @PathVariable("question-id") Long questionId,
            @RequestBody QuestionCommentPostDto questionCommentPostDto) throws Exception {

        questionCommentService.createQuestionComment(questionCommentPostDto, questionId);
    }
}
