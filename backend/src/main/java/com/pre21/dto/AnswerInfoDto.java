package com.pre21.dto;

import com.pre21.entity.Adoption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AnswerInfoDto {
    private Long questionId;
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private Adoption adoption;
    private Long vote;
}
