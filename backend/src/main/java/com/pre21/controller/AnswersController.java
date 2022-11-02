package com.pre21.controller;

import com.pre21.dto.AnswerPatchDto;
import com.pre21.dto.AnswersDto;
import com.pre21.dto.QuestionDto;
import com.pre21.entity.Answers;
import com.pre21.mapper.AnswersMapper;
import com.pre21.service.AnswersService;
import com.pre21.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pre21.util.RestConstants.*;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class AnswersController {
    private final AnswersService answersService;
    private final LikeService likeService;
    private final AnswersMapper mapper;


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

    /**
     * 답변 patch 요청에 대한 컨트롤러 메서드입니다.
     *
     * @param userId         쿠키에서 값을 받아옵니다.
     * @param answerPatchDto 답변 수정 요청입니다.
     * @param answerId       수정한 답변의 Id입니다.
     * @author dev32user
     */
    @PatchMapping("/answer/{answer-id}/edit")
    public ResponseEntity patchAnswer(
            @CookieValue(name = "userId") Long userId,
            @PathVariable("answer-id") Long answerId,
            @RequestBody AnswerPatchDto answerPatchDto) {
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
}
