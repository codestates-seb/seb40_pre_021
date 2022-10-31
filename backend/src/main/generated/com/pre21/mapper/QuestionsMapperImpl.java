package com.pre21.mapper;

import com.pre21.dto.QuestionsPostDto;
import com.pre21.dto.QuestionsResponseDto;
import com.pre21.dto.QuestionsTagsResponseDto;
import com.pre21.entity.Questions;
import com.pre21.entity.QuestionsTags;
import com.pre21.entity.Tags;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T04:46:59+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class QuestionsMapperImpl implements QuestionsMapper {

    @Override
    public Questions questionsPostToQuestion(QuestionsPostDto questionsPostDto) {
        if ( questionsPostDto == null ) {
            return null;
        }

        Questions questions = new Questions();

        questions.setTitle( questionsPostDto.getTitle() );
        questions.setContents( questionsPostDto.getContents() );

        return questions;
    }

    @Override
    public QuestionsResponseDto questionsToQuestionResponse(Questions questions, List<Tags> tags) {
        if ( questions == null && tags == null ) {
            return null;
        }

        List<QuestionsTagsResponseDto> questionsTags = null;
        if ( questions != null ) {
            questionsTags = questionsTagsListToQuestionsTagsResponseDtoList( questions.getQuestionsTags() );
        }

        Long questionId = null;

        QuestionsResponseDto questionsResponseDto = new QuestionsResponseDto( questionId, questionsTags );

        return questionsResponseDto;
    }

    protected QuestionsTagsResponseDto questionsTagsToQuestionsTagsResponseDto(QuestionsTags questionsTags) {
        if ( questionsTags == null ) {
            return null;
        }

        Long tagId = null;
        String title = null;

        QuestionsTagsResponseDto questionsTagsResponseDto = new QuestionsTagsResponseDto( tagId, title );

        return questionsTagsResponseDto;
    }

    protected List<QuestionsTagsResponseDto> questionsTagsListToQuestionsTagsResponseDtoList(List<QuestionsTags> list) {
        if ( list == null ) {
            return null;
        }

        List<QuestionsTagsResponseDto> list1 = new ArrayList<QuestionsTagsResponseDto>( list.size() );
        for ( QuestionsTags questionsTags : list ) {
            list1.add( questionsTagsToQuestionsTagsResponseDto( questionsTags ) );
        }

        return list1;
    }
}
