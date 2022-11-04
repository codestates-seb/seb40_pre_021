package com.pre21.controller;

import com.pre21.dto.QuestionDto;
import com.pre21.entity.Questions;
import com.pre21.mapper.QuestionsMapper;
import com.pre21.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    private final QuestionsMapper mapper;


    @GetMapping("/search/{path:.+}")
    public ResponseEntity searchingKeyword(@PathVariable String path) {
        List<Questions> searchResult = searchService.findQuestionForKeyword(path);
        int totalSearchCount = searchResult.size();
        List<QuestionDto.GetResponseDtos> response = mapper.questionsToQuestionResponses(searchResult);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
