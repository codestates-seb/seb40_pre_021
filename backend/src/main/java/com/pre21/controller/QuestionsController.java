package com.pre21.controller;

import com.pre21.dto.QuestionPatchDto;
import com.pre21.dto.QuestionsPostDto;
import com.pre21.entity.Questions;
import com.pre21.mapper.QuestionsMapper;
import com.pre21.service.QuestionsService;
import com.pre21.util.dto.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pre21.security.utils.JwtConstants.REFRESH_TOKEN;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionsController {
    private final QuestionsService questionsService;
    private final QuestionsMapper mapper;

    // 질문 생성
    @PostMapping("/ask")
    public void createQuestion(@RequestBody QuestionsPostDto questionsPostDto) throws Exception {
        // Questions questions = mapper.questionsPostToQuestion(questionsPostDto);

        questionsService.createQuestion(questionsPostDto);

        // return new ResponseEntity(questions, HttpStatus.CREATED);

       /* return new ResponseEntity<>(mapper.questionsToQuestionResponse(createdQuestion, null),
                HttpStatus.CREATED);*/
    }


    // 질문 조회
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
        Questions questions = questionsService.patchQuestion(userId,questionId, questionPatchDto);
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
    public void deleteQuestions(@PathVariable("question-id") Long questionId) throws Exception {
        questionsService.deleteQuestion(questionId);
    }


    /**
     *
     * @param questionId : 질문식별자
     * @param answerId : 답변식별자
     * @param userId : 로그인 유저식별자
     * @author mozzi327
     */
    @GetMapping("/question/{question-id}/adopt/{answer-id}")
    public void adoptQuestion(@PathVariable("question-id") Long questionId,
                                 @PathVariable("answer-id") Long answerId,
                                 @CookieValue(name = "userId", required = true) Long userId) {
        questionsService.adoptingQuestion(questionId, answerId, userId);

    }
}
