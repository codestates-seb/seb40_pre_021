package com.pre21.dto;

import com.pre21.entity.QuestionsTags;
import com.pre21.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsPostDto {
    private String title;
    private String contents;
    private List<String> tags;
}
