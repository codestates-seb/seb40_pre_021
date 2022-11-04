package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionLikeResponseDto {
    private Long userId;
    private String nickname;
    private boolean likeYn;
    private boolean unlikeYn;
}
