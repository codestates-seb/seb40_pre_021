package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * QuestionComment 생성 Post 요청 시 Dto입니다. <br>
 * RequestBody에 해당합니다.
 *
 * @author dev32user
 */
@Getter
@Setter
@AllArgsConstructor
public class QuestionCommentPostDto {
    private Long userId;
    private String comments;
}
