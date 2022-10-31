package com.pre21.controller;

import com.pre21.dto.AnswersDto;
import com.pre21.entity.Answers;
import com.pre21.entity.Questions;
import com.pre21.service.AnswersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class AnswersController {
    private final AnswersService answersService;


    @PostMapping("/{question-id}/answer")
    public void createAnswer(@PathVariable("question-id") Long questionId,
                             @RequestBody AnswersDto.Post answerPostDto) {

        answersService.createAnswer(answerPostDto, questionId);
    }
}
