package com.pre21.controller;

import com.pre21.dto.*;
import com.pre21.entity.Answers;
import com.pre21.entity.Questions;
import com.pre21.mapper.AnswersMapper;
import com.pre21.mapper.QuestionsMapper;
import com.pre21.service.AnswersService;
import com.pre21.service.LikeService;
import com.pre21.service.QuestionsService;
import com.pre21.util.dto.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/questions")
@RequiredArgsConstructor
public class QuestionsController {
    private final QuestionsService questionsService;
    private final LikeService likeService;
    private final QuestionsMapper mapper;
    private final AnswersService answersService;
    private final AnswersMapper answersMapper;

    /**
     * 질문 생성 메서드
     * @param post 질문 생성 Dto
     * @param userId 쿠키에 담긴 유저 Id
     * @author LimJaeminZ
     */
    @PostMapping("/ask")
    public ResponseEntity createQuestion(@RequestBody QuestionDto.Post post,
                                         @CookieValue(name = "userId") Long userId) {
        Questions questions = questionsService.createQuestion(post, userId);

        return new ResponseEntity<>(
                QuestionDto.CreateResponse
                        .builder()
                        .id(questions.getId())
                        .build(),
                HttpStatus.CREATED);
    }


    /**
     * 질문 상세 조회 메서드
     * @param questionId 질문 식별자
     * @author LimJaeminZ
     */
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") Long questionId) {
        Questions questions = questionsService.findQuestion(questionId);

        return new ResponseEntity<>(mapper.questionsToQuestionResponse(questions),
                HttpStatus.OK);
    }

    /**
     * 질문 patch 요청에 대한 컨트롤러 메서드
     * @param userId 쿠키에 담긴 유저Id
     * @param patch 질문 수정 요청
     * @param questionId 수정한 질문Id
     * @author dev32user
     */
    @PatchMapping("/{question-id}/edit")
    public ResponseEntity patchQuestion(@CookieValue(name = "userId") Long userId,
                                        @PathVariable("question-id") Long questionId,
                                        @RequestBody QuestionDto.Patch patch) {
        Questions questions = questionsService.patchQuestion(userId, questionId, patch);

        return new ResponseEntity(mapper.questionsToQuestionResponse(questions), HttpStatus.OK);
    }

    /**
     * 질문 전체 조회 메서드
     * @return 생성된 질문 전체 + 질문 개수
     * @author LimJaeminZ
     */
    @GetMapping
    public ResponseEntity getQuestions() {
        List<Questions> questions = questionsService.findQuestions();
        long questionsCount = questionsService.findQuestionCount();
        List<QuestionDto.GetResponseDtos> response = mapper.questionsToQuestionResponses(questions);
        Collections.sort(response);

        return new ResponseEntity<>(
                new MultiResponseDto.MultiResponseDtos<>(response, questionsCount),
                HttpStatus.OK);
    }

    /**
     * Pagination 질문 전체 조회 메서드
     * @param page 페이지
     * @param size 페이지에 출력되는 개수
     * @return 생성된 질문 중 size 개수 + pageInfo
     * @author LimJaeminZ
     */
    @GetMapping("/question")
    public ResponseEntity getPagingQuestions(@RequestParam int page,
                                             @RequestParam int size) {
        Page<Questions> questionsPage = questionsService.findPageQuestions(page - 1, size);
        List<Questions> questions = questionsPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionsToQuestionResponses(questions), questionsPage),
                HttpStatus.OK);
    }

    /**
     * 질문 삭제 메서드
     * @param questionId 삭제할 질문Id
     * @param userId 쿠키에 담긴 유저Id
     * @author LimJaeminZ
     */
    @DeleteMapping("/delete/{question-id}")
    public void deleteQuestions(@PathVariable("question-id") Long questionId,
                                @CookieValue(name = "userId") Long userId) {
        questionsService.deleteQuestion(questionId, userId);
    }


    /**
     * 질문 작성자 채택 메서드
     * @param questionId 질문식별자
     * @param answerId 답변식별자
     * @param userId 로그인 유저식별자
     * @author mozzi327
     */
    @GetMapping("/question/{question-id}/adopt/{answer-id}")
    public void adoptQuestion(@PathVariable("question-id") Long questionId,
                              @PathVariable("answer-id") Long answerId,
                              @CookieValue(name = "userId") Long userId) {
        questionsService.adoptingQuestion(questionId, answerId, userId);
    }

    /**
     * 질문 북마크 메서드
     * @param questionId 북마크 처리 할 질문Id
     * @param userId 쿠키에 담긴 유저Id
     * @author mozzi327
     */
    @PostMapping("/bookmark/{question-id}")
    public void clickQuestionBookmark(@PathVariable("question-id") Long questionId,
                                      @CookieValue(name = "userId") Long userId) {
        questionsService.addQuestionBookmark(questionId, userId);
    }

    /**
     * 답변 북마크 메서드
     * @param questionId 답변이 달린 질문Id
     * @param answerId 북마크 처리 할 답변Id
     * @param userId 쿠키에 담긴 유저Id
     * @author LimJaeminZ
     */
    @PostMapping("/bookmark/{question-id}/{answer-id}")
    public void clickAnswerBookmark(@PathVariable("question-id") Long questionId,
                                    @PathVariable("answer-id") Long answerId,
                                    @CookieValue(name = "userId") Long userId) {
        questionsService.addAnswerBookmark(questionId, answerId, userId);
    }

    /**
     * 질문에 대한 댓글 생성
     * @param questionId 댓글을 생성하는 질문Id
     * @param commentPost 댓글을 생성하는 요청의 RequestBody
     * @author dev32user
     */
    @PostMapping("/question/comment/{question-id}")
    public void createQuestionComment(
            @PathVariable("question-id") Long questionId,
            @CookieValue(name = "userId") Long userId,
            @RequestBody QuestionDto.CommentPost commentPost) throws Exception {

        questionsService.createQuestionComment(commentPost, userId, questionId);
    }


    /**
     * 질문 좋아요,싫어요 메서드
     * @param questionId 질문식별자
     * @param request 좋아요, 싫어요 요청 RequestBody
     * @author dev32user
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/question/{question-id}/like")
    public void clickQuestionLike (@PathVariable("question-id") Long questionId,
                                   @CookieValue("userId") Long userId,
                                   @RequestBody QuestionDto.Like request) {
        likeService.saveQuestionLike(questionId, request, userId);
    }

    /**
     * 답변 생성 메서드
     * @param questionId 답변을 생성할 질문Id
     * @param userId 쿠키에서 가져온 유저Id
     * @param answerPostDto 답변 생성 요청 RequestBody
     * @author LimJaeminZ
     */
    @PostMapping("/answer/{question-id}")
    public void createAnswer(@PathVariable("question-id") Long questionId,
                             @CookieValue(name = "userId") Long userId,
                             @RequestBody AnswerDto.Post answerPostDto) {

        answersService.createAnswer(answerPostDto, questionId, userId);
    }

    /**
     * 답변 좋아요, 싫어요
     * @param answerId 좋아요, 싫어요 기능을 적용할 답변Id
     * @param userId 쿠키에 담긴 유저Id
     * @param request 좋아요, 싫어요 요청 RequestBody
     * @author mozzi327
     */
    @PostMapping("/answer/{answer-id}/like")
    public void clickAnswerLike(@PathVariable("answer-id") Long answerId,
                                @CookieValue("userId") Long userId,
                                @RequestBody QuestionDto.Like request) {

        likeService.saveAnswerLike(answerId, request, userId);
    }

    /**
     * 답변 patch 요청에 대한 컨트롤러 메서드
     * @param userId 쿠키에서 가져운 유저Id
     * @param answerPatchDto 답변 수정 요청
     * @param answerId 수정한 답변Id
     * @author dev32user
     */
    @PatchMapping("/answer/{answer-id}/edit")
    public ResponseEntity patchAnswer (
            @CookieValue(name = "userId") Long userId,
            @PathVariable("answer-id") Long answerId,
            @RequestBody AnswerDto.Patch answerPatchDto) {
        Answers answers = answersService.patchAnswer(userId, answerId, answerPatchDto);
        return new ResponseEntity(answersMapper.answerToAnswerResponse(answers), HttpStatus.OK);
    }


    /**
     * 답번 delete 요청에 대한 컨트롤러 메서드
     * @param userId Long 타입의 삭제를 요청한 쿠키 유저Id
     * @param answerId Long 타입의 삭제 할 답변Id
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
     * @param answerId 댓글을 생성하는 답변Id
     * @param answerCommentPostDto 댓글을 생성하는 요청의 RequestBody
     * @author dev32user
     */
    @PostMapping("/answer/{answer-id}/comment")
    public void createAnswerComment(
            @PathVariable("answer-id") Long answerId,
            @CookieValue(value = "userId", required = false) Long userId,
            @RequestBody AnswerDto.CommentPost answerCommentPostDto) throws Exception {

        answersService.createAnswerComment(answerCommentPostDto, userId, answerId);
    }
}
