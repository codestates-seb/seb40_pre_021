package com.pre21.controller;

import com.pre21.dto.AnswerDto;
import com.pre21.dto.QuestionDto;
import com.pre21.entity.Answers;
import com.pre21.mapper.AnswersMapper;
import com.pre21.service.AnswersService;
import com.pre21.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class AnswersController {
    private final AnswersService answersService;
    private final LikeService likeService;
    private final AnswersMapper mapper;


    @PostMapping("/answer/{question-id}")
    public void createAnswer(@PathVariable("question-id") Long questionId,
                             @CookieValue(value = "userID", required = false) Long userId,
                             @RequestBody AnswerDto.Post answerPostDto) {

        answersService.createAnswer(answerPostDto, questionId, userId);
    }

    @PostMapping("/answer/{answer-id}/like")
    public void clickAnswerLike(@PathVariable("answer-id") Long answerId,
                                @RequestBody QuestionDto.Like request) {

        likeService.saveAnswerLike(answerId, request);
    }

    /**
     * 답변 patch 요청에 대한 컨트롤러 메서드입니다.
     *
     * @param userId         쿠키에서 값을 받아옵니다.
     * @param answerPatchDto 답변 수정 요청입니다.
     * @param answerId       수정한 답변의 Id입니다.
     * @author dev32user
     */
    @PatchMapping("/answer/{answer-id}/edit")
    public ResponseEntity patchAnswer (
            @CookieValue(name = "userId") Long userId,
            @PathVariable("answer-id") Long answerId,
            @RequestBody AnswerDto.Patch answerPatchDto) {
        Answers answers = answersService.patchAnswer(userId, answerId, answerPatchDto);
        return new ResponseEntity(mapper.answerToAnswerResponse(answers), HttpStatus.OK);
    }


    /**
     * 답번 delete 요청에 대한 컨트롤러 메서드입니다.
     *
     * @param userId   Long 타입의 삭제를 요청한 사용자의 Id 값입니다. 쿠키에서 값을 받아옵니다.
     * @param answerId Long 타입의 삭제 할 답변의 Id 값입니다.
     * @author dev32user
     */
    @DeleteMapping("/answer/{answer-id}/delete")
    public ResponseEntity deleteAnswer(
            @CookieValue(name = "userId") Long userId,
            @PathVariable("answer-id") Long answerId) {
        answersService.deleteAnswer(userId, answerId);
        return new ResponseEntity(HttpStatus.OK);

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
            @CookieValue(value = "userId", required = false) Long userId,
            @RequestBody AnswerDto.CommentPost answerCommentPostDto) throws Exception {

        answersService.createAnswerComment(answerCommentPostDto, userId, answerId);
    }
}
