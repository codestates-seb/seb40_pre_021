package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


public class SearchDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response <T>{
        private int searchCount;
        private T data;
    }
}
