package com.pre21.controller;

import com.pre21.dto.QuestionPatchDto;
import com.pre21.dto.QuestionsPostDto;
import com.pre21.dto.QuestionsResponseDto;
import com.pre21.entity.Questions;
import com.pre21.mapper.QuestionsMapper;
import com.pre21.service.QuestionsService;
import com.pre21.util.dto.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionsController {
    private final QuestionsService questionsService;
    private final QuestionsMapper mapper;

    /**
     * 질문 생성 메서드
     *
     * @param questionsPostDto  : 질문 생성 Dto
     * @param userId            : 쿠키에 담긴 유저 Id
     */
    @PostMapping("/ask")
    public void createQuestion(@RequestBody QuestionsPostDto questionsPostDto,
                               @CookieValue(name = "userId", required = true) Long userId) {

        questionsService.createQuestion(questionsPostDto, userId);

    }


    /**
     * 질문 상세 조회 메서드
     *
     * @param questionId : 질문 식별자
     */
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") Long questionId) {
        Questions questions = questionsService.findQuestion(questionId);

        return new ResponseEntity<>(mapper.questionsToQuestionResponse(questions),
                HttpStatus.OK);
    }

    /**
     * 질문 patch 요청에 대한 컨트롤러 메서드입니다.
     *
     * @param userId           쿠키에서 값을 받아옵니다.
     * @param questionPatchDto 질문 수정 요청입니다.
     * @param questionId       수정한 질문의 Id입니다.
     * @author dev32user
     */
    @PatchMapping("/{question-id}/edit")
    public ResponseEntity patchQuestion(
            @CookieValue(name = "userId") Long userId,
            @PathVariable("question-id") Long questionId,
            @RequestBody QuestionPatchDto questionPatchDto) {
        Questions questions = questionsService.patchQuestion(userId, questionId, questionPatchDto);
        return new ResponseEntity(mapper.questionsToQuestionResponse(questions), HttpStatus.OK);
    }


    // 질문 전체 조회
    @GetMapping
    public ResponseEntity getQuestions() {

        List<Questions> questions = questionsService.findQuestions();
        long questionsCount = questionsService.findQuestionCount();


        return new ResponseEntity<>(
                new MultiResponseDto.MultiResponseDtos<>(mapper.questionsToQuestionResponses(questions), questionsCount),
                HttpStatus.OK);
    }

    // Pagination + 질문 전체 조회
    @GetMapping("/questions")
    public ResponseEntity getPagingQuestions(@RequestParam int page,
                                             @RequestParam int size) {
        Page<Questions> questionsPage = questionsService.findPageQuestions(page - 1, size);
        List<Questions> questions = questionsPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionsToQuestionResponses(questions), questionsPage),
                HttpStatus.OK);
    }

    // 질문 삭제
    @DeleteMapping("/delete/{question-id}")
    public void deleteQuestions(@PathVariable("question-id") Long questionId,
                                @CookieValue(name = "userId") Long userId) {
        questionsService.deleteQuestion(questionId, userId);
    }


    /**
     * @method 질문 작성자 채택 기능
     * @param questionId : 질문식별자
     * @param answerId   : 답변식별자
     * @param userId     : 로그인 유저식별자
     * @author mozzi327
     */
    @GetMapping("/question/{question-id}/adopt/{answer-id}")
    public void adoptQuestion(@PathVariable("question-id") Long questionId,
                              @PathVariable("answer-id") Long answerId,
                              @CookieValue(name = "userId", required = true) Long userId) {
        questionsService.adoptingQuestion(questionId, answerId, userId);

    }

    /**
     * @method 질문 북마크 추가
     * @param questionId
     * @param userId
     * @author mozzi327
     */
    @PostMapping("/bookmark/{question-id}")
    public void clickQuestionBookmark(@PathVariable("question-id") Long questionId,
                                      @CookieValue(name = "userId", required = true) Long userId) {
        questionsService.addQuestionBookmark(questionId, userId);

    }

    @PostMapping("/bookmark/{question-id}/{answer-id}")
    public void clickAnswerBookmark(@PathVariable("question-id") Long questionId,
                                    @PathVariable("answer-id") Long answerId,
                                    @CookieValue(name = "userId", required = true) Long userId) {
        questionsService.addAnswerBookmark(questionId, answerId, userId);
    }
}
