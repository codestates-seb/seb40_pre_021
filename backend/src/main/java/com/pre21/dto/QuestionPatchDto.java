package com.pre21.dto;

import com.pre21.entity.QuestionsTags;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuestionPatchDto {
    private String title;
    private String contents;
    private List<QuestionsTags> tags;
}
