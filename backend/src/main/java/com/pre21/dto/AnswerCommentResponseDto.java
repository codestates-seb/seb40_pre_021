package com.pre21.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCommentResponseDto {
    private Long id;
    private String comments;
    private LocalDateTime createdAt;
    private String nickname;
}
