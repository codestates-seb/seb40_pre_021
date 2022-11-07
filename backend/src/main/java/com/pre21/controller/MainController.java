package com.pre21.controller;

import com.pre21.dto.MainDto;
import com.pre21.dto.QuestionDto;
import com.pre21.dto.SearchDto;
import com.pre21.entity.Questions;
import com.pre21.mapper.QuestionsMapper;
import com.pre21.service.QuestionsService;
import com.pre21.service.SearchService;
import com.pre21.service.TagsService;
import com.pre21.util.dto.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
//@RequestMapping("/questions")
@RequiredArgsConstructor
public class MainController {
    private final SearchService searchService;
    private final QuestionsService questionsService;
    private final TagsService tagsService;
    private final QuestionsMapper mapper;

    /**
     * 사용자 검색을 위한 컨트롤러 메서드
     * @param ask 검색 키워드
     * @return ResponseEntity
     * @author mozzi327
     */
    @GetMapping("/search/{ask:.+}")
    public ResponseEntity searchingKeyword(@PathVariable String ask) {
        List<Questions> searchResult = searchService.findQuestionForKeyword(ask);
        int totalSearchCount = searchResult.size();
        List<QuestionDto.GetResponseDtos> resultDto = mapper.questionsToQuestionResponses(searchResult);
        SearchDto.Response response = SearchDto.Response.builder()
                .searchCount(totalSearchCount)
                .data(resultDto)
                .build();

        return new ResponseEntity(response, HttpStatus.OK);
    }


    /**
     * 질문 전체 조회 메서드
     * @return  생성된 질문 전체 + 질문 개수
     */
    @GetMapping
    public ResponseEntity getQuestions() {

        List<Questions> questions = questionsService.findQuestions();
        List<String> tags = tagsService.findAllTags();
        List<QuestionDto.GetResponseDtos> response = mapper.questionsToQuestionResponses(questions);
        Collections.sort(response);

        long questionsCount = questionsService.findQuestionCount();
        return new ResponseEntity<>(
                MainDto.MainPage
                        .builder()
                        .questionsCount(questionsCount)
                        .data(response)
                        .tags(tags)
                        .build(),
                HttpStatus.OK);
    }
}
