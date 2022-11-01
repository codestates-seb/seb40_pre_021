package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * AnswerComment 생성 Post 요청 시 Dto입니다. <br>
 * RequestBody에 해당합니다.
 *
 * @author dev32user
 */
@Getter
@Setter
@AllArgsConstructor
public class AnswerCommentPostDto {
    private Long userId;
    private String comments;
}
