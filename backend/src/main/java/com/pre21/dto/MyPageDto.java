package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MyPageDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class TagResponse {
        private Long tagId;
        private String title;
        private long tagCount;
    }

}
