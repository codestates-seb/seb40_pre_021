package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@AllArgsConstructor
public class QuestionsTagsResponseDto {
    private Long tagId;
    private String title;
}

