package com.pre21.controller;

import com.pre21.dto.*;
import com.pre21.entity.Questions;
import com.pre21.mapper.QuestionsMapper;
import com.pre21.service.LikeService;
import com.pre21.service.QuestionsService;
import com.pre21.util.dto.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pre21.security.utils.ControllerConstants.USER_ID;

@Slf4j
@RestController
@RequestMapping(value = "/questions")
@RequiredArgsConstructor
public class QuestionsController {
    private final QuestionsService questionsService;
    private final LikeService likeService;
    private final QuestionsMapper mapper;

    /**
     * 질문 생성 메서드
     * @param post   질문 생성 Dto
     * @param userId 쿠키에 담긴 유저 Id
     */
    @PostMapping("/ask")
    public void createQuestion(@RequestBody QuestionDto.Post post,
                               @CookieValue(name = "userId", required = true) Long userId) {

        questionsService.createQuestion(post, userId);
    }


    /**
     * 질문 상세 조회 메서드
     * @param questionId 질문 식별자
     */
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") Long questionId) {
        Questions questions = questionsService.findQuestion(questionId);

        return new ResponseEntity<>(mapper.questionsToQuestionResponse(questions),
                HttpStatus.OK);
    }

    /**
     * 질문 patch 요청에 대한 컨트롤러 메서드
     * @param userId     쿠키에 담긴 유저Id
     * @param patch      질문 수정 요청
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
     * @return  생성된 질문 전체 + 질문 개수
     */
    @GetMapping
    public ResponseEntity getQuestions() {

        List<Questions> questions = questionsService.findQuestions();
        long questionsCount = questionsService.findQuestionCount();

        return new ResponseEntity<>(
                new MultiResponseDto.MultiResponseDtos<>(mapper.questionsToQuestionResponses(questions), questionsCount),
                HttpStatus.OK);
    }

    /**
     * Pagination 질문 전체 조회 메서드
     * @param page  페이지
     * @param size  페이지에 출력되는 개수
     * @return 생성된 질문 중 size 개수 + pageInfo
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
     * @param questionId    삭제할 질문Id
     * @param userId        쿠키에 담긴 유저Id
     */
    @DeleteMapping("/delete/{question-id}")
    public void deleteQuestions(@PathVariable("question-id") Long questionId,
                                @CookieValue(name = "userId") Long userId) {
        questionsService.deleteQuestion(questionId, userId);
    }


    /**
     * 질문 작성자 채택 메서드
     * @param questionId : 질문식별자
     * @param answerId   : 답변식별자
     * @param userId     : 로그인 유저식별자
     * @author mozzi327
     */
    @GetMapping("/question/{question-id}/adopt/{answer-id}")
    public void adoptQuestion(@PathVariable("question-id") Long questionId,
                              @PathVariable("answer-id") Long answerId,
                              @CookieValue(name = USER_ID, required = false) Long userId) {
        questionsService.adoptingQuestion(questionId, answerId, userId);
    }

    /**
     * 질문 북마크 메서드
     * @param questionId    북마크 처리 할 질문Id
     * @param userId        쿠키에 담긴 유저Id
     * @author mozzi327
     */
    @PostMapping("/bookmark/{question-id}")
    public void clickQuestionBookmark(@PathVariable("question-id") Long questionId,
                                      @CookieValue(name = USER_ID, required = true) Long userId) {
        questionsService.addQuestionBookmark(questionId, userId);
    }

    /**
     * 답변 북마크 메서드
     * @param questionId    답변이 달린 질문Id
     * @param answerId      북마크 처리 할 답변Id
     * @param userId        쿠키에 담긴 유저Id
     */
    @PostMapping("/bookmark/{question-id}/{answer-id}")
    public void clickAnswerBookmark(@PathVariable("question-id") Long questionId,
                                    @PathVariable("answer-id") Long answerId,
                                    @CookieValue(name = USER_ID, required = true) Long userId) {
        questionsService.addAnswerBookmark(questionId, answerId, userId);
    }

    /**
     * 질문에 대한 댓글 생성
     * @param questionId             댓글을 생성하는 질문Id
     * @param CommentPost 댓글을 생성하는 요청의 RequestBody
     * @author dev32user
     */
    @PostMapping("/question/comment/{question-id}")
    public void createQuestionComment(
            @PathVariable("question-id") Long questionId,
            @CookieValue(name = "userId") Long userId,
            @RequestBody QuestionDto.CommentPost CommentPost) throws Exception {

        questionsService.createQuestionComment(CommentPost, userId, questionId);
    }


    /**
     * 질문 좋아요,싫어요 메서드
     * @param questionId 질문식별자
     * @param request
     * @author dev32user
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/question/{question-id}/like")
    public void clickQuestionLike (@PathVariable("question-id") Long questionId,
                                   @RequestBody QuestionDto.Like request) {
        likeService.saveQuestionLike(questionId, request);
    }
}
