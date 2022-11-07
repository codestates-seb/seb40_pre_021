package com.pre21.mapper;

import com.pre21.dto.AnswerDto;
import com.pre21.dto.MyPageDto;
import com.pre21.entity.Answers;
import com.pre21.entity.QuestionsTags;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

import static com.pre21.util.RestConstants.QUESTION_URL;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AnswersMapper {
    List<MyPageDto.AnswerInfo> answersToAnswerResponses(List<Answers> answers);

    @Mapping(source = "answers.questions.id", target = "questionId")
    @Mapping(source = "answers.questions.title", target = "title")
    MyPageDto.AnswerInfo answersToAnswerResponses(Answers answers);

    /**
     * @param answers Answers 객체를 포함한 List 객체
     * @return List  MyPageDto.AnswerInfos 객체를 포함한 List 반환
     * @author Mozzi327
     */
    default List<MyPageDto.AnswerInfos> answerToAnswerResponses(List<Answers> answers) {
        return answers.stream()
                .map(answer -> MyPageDto.AnswerInfos
                        .builder()
                        .questionId(answer.getQuestionsId())
                        .id(answer.getId())
                        .title(answer.getQuestions().getTitle())
                        .createdAt(answer.getCreatedAt())
                        .choosed(answer.isChooseYn())
                        .vote(answer.getVote())
                        .tags(answer.getQuestions().getQuestionsTags()
                                .stream()
                                .map(QuestionsTags::getTagValue)
                                .collect(Collectors.toList()))
                        .url(QUESTION_URL + answer.getQuestions().getId())
                        .build()).collect(Collectors.toList());
    }


    /**
     * 답변 수정 요청 시 반환 Dto 생성용 매퍼 메서드
     *
     * @param answers   패치된 질문 객체
     * @author Mozzi327
     */
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
