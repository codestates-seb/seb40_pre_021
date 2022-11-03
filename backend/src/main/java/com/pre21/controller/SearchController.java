package com.pre21.controller;

import com.pre21.dto.QuestionDto;
import com.pre21.entity.Questions;
import com.pre21.mapper.QuestionsMapper;
import com.pre21.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/search")
@Slf4j
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    private final QuestionsMapper mapper;


    @RequestMapping(value = "/{path}", method = RequestMethod.GET)
    public ResponseEntity searchingKeyword(@PathVariable String path, HttpServletRequest request) {
        log.info("########################## keyword ################### -> {}", request);

        List<Questions> searchResult = searchService.findQuestionForKeyword(path);
        int totalSearchCount = searchResult.size();
        List<QuestionDto.GetResponseDtos> response = mapper.questionsToQuestionResponses(searchResult);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
