package com.pre21.controller;

import com.pre21.dto.QuestionDto;
import com.pre21.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class LikesController {

    private final LikeService likeService;

    // 추천-비추천 버튼 눌렀을 때 요청
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/question/{question-id}/like")
    public void clickQuestionLike (@PathVariable("question-id") Long questionId
            ,@RequestBody QuestionDto.Like request) {
        likeService.saveQuestionLike(questionId, request);
    }



}
