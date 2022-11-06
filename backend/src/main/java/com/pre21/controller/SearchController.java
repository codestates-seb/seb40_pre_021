package com.pre21.controller;

import com.pre21.dto.QuestionDto;
import com.pre21.dto.SearchDto;
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
//@RequestMapping("/questions")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
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
}
