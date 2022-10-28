package com.pre21.mapper;


import com.pre21.dto.QuestionsPostDto;
import com.pre21.dto.QuestionsResponseDto;
import com.pre21.dto.QuestionsTagsResponseDto;
import com.pre21.entity.Questions;
import com.pre21.entity.Tags;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionsMapper {
    Questions questionsPostToQuestion(QuestionsPostDto questionsPostDto);

    QuestionsResponseDto questionsToQuestionResponse(Questions questions, List<Tags> tags);

/*    default QuestionsResponseDto questionsToQuestionResponse(Questions questions) {
        return new QuestionsResponseDto(
                questions.getTitle(),
                questions.getContents(),
                questions.getQuestionsTags().stream()
                        .map(questionsTags -> new QuestionsTagsResponseDto(
                                questionsTags.getTags().getTagId(),
                                questionsTags.getTags().getTitle()
                        )).collect(Collectors.toList())
        );
    }*/
}
