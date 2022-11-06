package com.pre21.mapper;

import com.pre21.dto.MyPageDto;
import com.pre21.entity.Answers;
import com.pre21.entity.Bookmark;
import com.pre21.entity.QuestionsTags;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookmarkMapper {

    default List<MyPageDto.BookmarkInfo> bookmarkToBookmarkResponses(List<Bookmark> bookmarks) {
        return bookmarks.stream()
                .map(bookmark -> MyPageDto.BookmarkInfo
                        .builder()
                        .questionId(bookmark.getQuestions().getId())
                        .questionUser(bookmark.getUsers().getNickname())
                        .title(bookmark.getQuestions().getTitle())
                        .url(bookmark.getUrl())
                        .tag(questionTagsToTags(bookmark.getQuestions().getQuestionsTags()))
                        .vote(bookmark.getQuestions().getVote())
                        .choosed(bookmark.getQuestions().isChooseYn())
                        .views(bookmark.getQuestions().getViews())
                        .answerCount(bookmark.getQuestions().getAnswerCount())
                        .createdAt(bookmark.getCreatedAt())
                        .answer(bookmarkToAdoptedAnswer(
                                bookmark.getQuestions()
                                .getAdoption()
                                .getAnswers()))
                        .build()
                ).collect(Collectors.toList());
    }

    default MyPageDto.BookmarkAnswer bookmarkToAdoptedAnswer(Answers answers) {
        return MyPageDto.BookmarkAnswer
                .builder()
                .answerId(answers.getId())
                .answerUser(answers.getUsers().getNickname())
                .answerBody(answers.getContents())
                .answerCreatedAt(answers.getCreatedAt())
                .vote(answers.getVote())
                .choosed(answers.isChooseYn())
                .build();
    }


    default List<String> questionTagsToTags(List<QuestionsTags> questionsTags) {
        return questionsTags.stream()
                .map(questionsTag -> questionsTag.getTags().getTitle())
                .collect(Collectors.toList());
    }
}
