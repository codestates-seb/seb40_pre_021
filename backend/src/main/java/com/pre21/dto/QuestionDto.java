package com.pre21.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QuestionDto {

    @Getter
    @AllArgsConstructor
    public static class Like {
        private Long userId;
        private boolean likeYn;
        private boolean unlikeYn;
        private int count;
    }
}
