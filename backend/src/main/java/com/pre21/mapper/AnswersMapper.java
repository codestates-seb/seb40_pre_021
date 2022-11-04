package com.pre21.mapper;

import com.pre21.dto.AnswersDto;
import com.pre21.entity.Answers;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswersMapper {
    List<AnswerInfoDto> answerToAnswerResponse(List<Answers> answers);
    default AnswersDto.GetResponseDto answerToAnswerResponse(Answers answers){
        AnswersDto.GetResponseDto responseDto = new AnswersDto.GetResponseDto();

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
