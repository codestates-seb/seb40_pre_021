package com.pre21.util.dto;

import com.pre21.dto.QuestionDto;
import com.pre21.dto.QuestionsResponseDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiResponseDto <T> {
    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

    @Getter
    public static class MultiResponseDtos<T> {
        private List<T> data;

        private long questionsCount;

        public MultiResponseDtos(List<T> data, long questionsCount) {
            this.data = data;
            this.questionsCount = questionsCount;
        }
    }
}
