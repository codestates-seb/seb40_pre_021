package com.pre21.mapper;


import com.pre21.dto.*;
import com.pre21.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionsMapper {

    default QuestionDto.GetResponseDto questionsToQuestionResponse(Questions questions) {
        QuestionDto.GetResponseDto responseDto = new QuestionDto.GetResponseDto();

        responseDto.setQuestionId(questions.getId());   // 질문 Id 저장
        responseDto.setTitle(questions.getTitle()); // 질문 제목 저장
        responseDto.setContents(questions.getContents());   // 질문 내용 저장
        List<QuestionsTags> questionsTags = questions.getQuestionsTags();   // 질문에 사용된 태그 정보 추출
        responseDto.setVote(questions.getVote());   // 질문 추천수 저장
        List<Answers> answers = questions.getAnswers(); //질문에 달린 답글 정보 추출
        responseDto.setViews(questions.getViews()); // 질문 조회수 저장
        responseDto.setCreatedAt(questions.getCreatedAt()); // 질문 생성 일자 저장
        User user = questions.getUsers();   // 질문을 등록한 유저 추출
        responseDto.setNickName(user.getNickname());    // 추출한 유저 정보에서 닉네임 저장
        responseDto.setQuestionsTags(questionsTagsToQuestionsTagsResponseDto(questionsTags));   // 태그 정보 저장
        responseDto.setAnswers(answersToAnswersResponseDto(answers));   // 답글 정보 저장

        return responseDto;
    }

    default List<QuestionsTagsResponseDto> questionsTagsToQuestionsTagsResponseDto(List<QuestionsTags> questionsTags) {
        // 추출한 태그 정보를 추출하여 리스트로 저장
        return questionsTags.stream()
                .map(questionsTags1 -> QuestionsTagsResponseDto
                        .builder()
                        .tagId(questionsTags1.getTags().getId())
                        .title(questionsTags1.getTags().getTitle())
                        .build())
                .collect(Collectors.toList());
    }

    default List<AnswersDto.ResponseDto> answersToAnswersResponseDto(List<Answers> answers) {
        // 추출한 답글 정보를 추출하여 리스트로 저장
        return answers.stream()
                .map(answers1 -> AnswersDto.ResponseDto
                        .builder()
                        .answerId(answers1.getAnswerId())
                        .contents(answers1.getContents())
                        .build())
                .collect(Collectors.toList());

    }
}
