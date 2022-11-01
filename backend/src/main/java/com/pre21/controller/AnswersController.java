package com.pre21.controller;

import com.pre21.dto.AnswersDto;
import com.pre21.dto.QuestionDto;
import com.pre21.entity.Answers;
import com.pre21.entity.Questions;
import com.pre21.service.AnswersService;
import com.pre21.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class AnswersController {
    private final AnswersService answersService;
    private final LikeService likeService;


    @PostMapping("/{question-id}/answer")
    public void createAnswer(@PathVariable("question-id") Long questionId,
                             @RequestBody AnswersDto.Post answerPostDto) {

        answersService.createAnswer(answerPostDto, questionId);
    }

    @PostMapping("/answer/{answer-id}/like")
    public void clickAnswerLike(@PathVariable("answer-id") Long answerId,
                                @RequestBody QuestionDto.Like request) {

        likeService.saveAnswerLike(answerId, request);
    }
}
