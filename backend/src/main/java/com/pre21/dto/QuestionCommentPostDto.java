package com.pre21.dto;

import com.pre21.entity.Questions;
import com.pre21.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCommentPostDto {
    private Long userId;
    private String comments;
}
