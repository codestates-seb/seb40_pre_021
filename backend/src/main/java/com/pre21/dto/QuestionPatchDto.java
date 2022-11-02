package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuestionPatchDto {
    private String title;
    private String contents;
    private List<String> tags;
}
