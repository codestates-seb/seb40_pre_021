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
     * 질문에 대한 댓글 생성
     * POST "/questions/{question-id}/comment"
     * <p>
     * requestBody :
     * {"body":"string"}
     * <p>
     * responseBody :
     * NULL
     */
    @PostMapping("/questions/{question-id}/comment")
    public void createQuestionComment(
            @PathVariable("question-id") Long questionId,
            @RequestBody QuestionCommentPostDto QuestioncommentPostDto) throws Exception {

        questionCommentService.createQuestionComment(QuestioncommentPostDto, questionId);
    }
}
