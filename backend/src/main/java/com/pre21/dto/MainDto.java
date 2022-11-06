package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MainDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MainPage {
        private long questionsCount;
        private List<QuestionDto.GetResponseDtos> data;
        private List<String> tags;
    }

}
