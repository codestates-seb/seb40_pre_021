package com.pre21.mapper;

import com.pre21.dto.AnswerDto;
import com.pre21.dto.AnswerInfoDto;
import com.pre21.entity.Answers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AnswersMapper {
    List<AnswerInfoDto> answersToAnswerResponses(List<Answers> answers);

    @Mapping(source = "answers.questions.id", target = "questionId")
    @Mapping(source = "answers.questions.title", target = "title")
    AnswerInfoDto answersToAnswerResponses(Answers answers);

    default AnswerDto.GetResponse answerToAnswerResponse(Answers answers) {
        AnswerDto.GetResponse responseDto = new AnswerDto.GetResponse();

        responseDto.setAnswerId(answers.getId());
        responseDto.setContents(answers.getContents());
        responseDto.setVote(answers.getVote());
        responseDto.setAdoption(answers.getAdoption());
        responseDto.setCreatedAt(answers.getCreatedAt());
        responseDto.setModifiedAt(answers.getModifiedAt());
        responseDto.setUser(answers.getUsers());
        responseDto.setAnswerLikes(answers.getAnswersLike());
        responseDto.setQuestions(answers.getQuestions());

        return responseDto;
    }
}
