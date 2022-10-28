package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class QuestionsResponseDto {
    private Long questionId;
    private List<QuestionsTagsResponseDto> questionsTags;
}
