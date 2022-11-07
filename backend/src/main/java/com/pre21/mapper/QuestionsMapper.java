package com.pre21.mapper;


import com.pre21.dto.*;
import com.pre21.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

import static com.pre21.util.RestConstants.QUESTION_URL;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionsMapper {

    List<QuestionDto.GetResponseDtos> questionsToQuestionResponses(List<Questions> questions);


    @Mapping(source = "questions.users.nickname", target = "nickname")
    QuestionDto.GetResponseDtos questionsToQuestionResponses(Questions questions);


    default List<MyPageDto.QuestionInfo> questionsToMypageQuestionResponse(List<Questions> questions) {
        return questions.stream()
                .map(question -> MyPageDto.QuestionInfo
                        .builder()
                        .id(question.getId())
                        .title(question.getTitle())
                        .contents(question.getContents())
                        .tags(question.getQuestionsTags().stream()
                                .map(QuestionsTags::getTagValue)
                                .collect(Collectors.toList())
                        )
                        .vote(question.getVote())
                        .choosed(question.isChooseYn())
                        .views(question.getViews())
                        .createdAt(question.getCreatedAt())
                        .answerCount(question.getAnswerCount())
                        .url(QUESTION_URL + question.getId())
                        .build()).collect(Collectors.toList());
    }



    default QuestionDto.GetResponseDto questionsToQuestionResponse(Questions questions) {
        QuestionDto.GetResponseDto responseDto = new QuestionDto.GetResponseDto();

        responseDto.setQuestionId(questions.getId());   // 질문 Id 저장
        responseDto.setTitle(questions.getTitle()); // 질문 제목 저장
        responseDto.setContents(questions.getContents());   // 질문 내용 저장
        responseDto.setChooseYn(questions.isChooseYn());    // 질문 답변 채택 여부 저장

        List<QuestionsTags> questionsTags = questions.getQuestionsTags();   // 질문에 사용된 태그 정보 리스트
        responseDto.setQuestionsTags(questionsTagsToQuestionsTagsResponseDto(questionsTags));   // 태그 정보 저장

        responseDto.setVote(questions.getVote());   // 질문 추천수 저장

        List<Answers> answers = questions.getAnswers(); // 질문에 달린 답변 정보 리스트
        responseDto.setAnswers(answersToAnswersResponseDto(answers));   // 답변 정보 저장

        responseDto.setViews(questions.getViews()); // 질문 조회수 저장
        responseDto.setCreatedAt(questions.getCreatedAt()); // 질문 생성 일자 저장

        User user = questions.getUsers();   // 질문을 등록한 유저 추출
        responseDto.setNickname(user.getNickname());    // 추출한 유저 정보에서 닉네임 저장

        responseDto.setAnswerCount(questions.getAnswerCount()); // 질문에 달린 답글 개수

        List<QuestionComments> questionComments = questions.getComments();  // 질문에 달린 댓글 정보 리스트
        responseDto.setComments(questionCommentsToQuestionCommentsResponseDto(questionComments));   // 댓글 정보 저장

        List<Bookmark> bookmark = questions.getBookmarks(); // 질문을 북마크한 유저 정보 리스트
        responseDto.setBookmarks(questionBookmarkToQuestionBookmarkResponseDto(bookmark));  // 질문을 북마크한 유저 정보 저장

        List<QuestionLikes> questionsLikes = questions.getQuestionsLikes(); // 질문 좋아요,싫어요 유저 정보 리스트
        responseDto.setQuestionsLikes(questionLikesToQuestionLikesResponseDto(questionsLikes)); //질문 좋아요,싫어요 유저 정보 저장

        return responseDto;
    }

    // 태그 정보 저장
    default List<QuestionDto.TagResponse> questionsTagsToQuestionsTagsResponseDto(List<QuestionsTags> questionsTags) {
        // 질문에 달린 태그 정보를 리스트로 저장
        return questionsTags.stream()
                .map(questionsTags1 -> QuestionDto.TagResponse
                        .builder()
                        .tagId(questionsTags1.getTags().getId())
                        .title(questionsTags1.getTags().getTitle())
                        .build())
                .collect(Collectors.toList());
    }

    // 댓글 정보 저장
    default List<QuestionDto.CommentResponse> questionCommentsToQuestionCommentsResponseDto(List<QuestionComments> questionComments) {
        // 질문에 달린 댓글 정보를 리스트로 저장
        return questionComments.stream()
                .map(questionComments1 -> QuestionDto.CommentResponse
                        .builder()
                        .id(questionComments1.getId())
                        .comments(questionComments1.getComments())
                        .createdAt(questionComments1.getCreatedAt())
                        .nickname(questionComments1.getNickname())
                        .build())
                .collect(Collectors.toList());
    }


    // 답변 정보 저장
    default List<AnswerDto.Response> answersToAnswersResponseDto(List<Answers> answers) {
        // 질문에 달린 답변 정보를 리스트로 저장
        return answers.stream()
                .map(answers1 -> AnswerDto.Response
                        .builder()
                        .answerId(answers1.getId())
                        .contents(answers1.getContents())
                        .vote(answers1.getVote())
                        .chooseYn(answers1.isChooseYn())
                        .createdAt(answers1.getCreatedAt())
                        .modifiedAt(answers1.getModifiedAt())
                        .nickname(answers1.getUsers().getNickname())
                        .comments(answerCommentsToAnswerCommentsResponseDto(answers1.getComments()))
                        .bookmarks(answerBookmarkToAnswerBookmarkResponseDto(answers1.getBookmarks()))
                        .answerLikes(answerLikesToAnswerLikesResponseDto(answers1.getAnswersLike()))
                        .build())
                .collect(Collectors.toList());
    }


    default List<AnswerDto.CommentResponse> answerCommentsToAnswerCommentsResponseDto(List<AnswerComments> answerComments) {
        // 답글에 달린 댓글 정보를 리스트로 저장
        return answerComments.stream()
                .map(answerComments1 -> AnswerDto.CommentResponse
                        .builder()
                        .commentId(answerComments1.getId())
                        .comments(answerComments1.getComments())
                        .createdAt(answerComments1.getCreatedAt())
                        .nickname(answerComments1.getNickname())
                        .build()
                ).collect(Collectors.toList());
    }

    default List<QuestionDto.BookmarkResponse> questionBookmarkToQuestionBookmarkResponseDto(List<Bookmark> bookmarks) {
        // 질문을 북마크한 유저 정보를 리스트로 저장
        return bookmarks.stream()
                .filter(bookmark -> bookmark.getAnswers() == null)
                .map(bookmark -> QuestionDto.BookmarkResponse
                        .builder()
                        .id(bookmark.getId())
                        .userId(bookmark.getUsers().getId())
                        .nickname(bookmark.getUsers().getNickname())
                        .build()
                ).collect(Collectors.toList());
    }

    default List<AnswerDto.BookmarkResponse> answerBookmarkToAnswerBookmarkResponseDto(List<Bookmark> bookmarks) {
        // 답변을 북마크한 유저 정보를 리스트로 저장
        return bookmarks.stream()
                .filter(bookmark -> bookmark.getAnswers() != null)
                .map(bookmark -> AnswerDto.BookmarkResponse
                        .builder()
                        .bookmarkId(bookmark.getId())
                        .userId(bookmark.getUsers().getId())
                        .nickname(bookmark.getUsers().getNickname())
                        .build()
                ).collect(Collectors.toList());
    }

    default List<QuestionDto.LikeResponse> questionLikesToQuestionLikesResponseDto(List<QuestionLikes> questionLikes) {
        // 질문에 좋아요,싫어요를 체크한 유저 정보를 리스트로 저장
        return questionLikes.stream()
                .map(questionLikes1 -> QuestionDto.LikeResponse
                        .builder()
                        .userId(questionLikes1.getUsers().getId())
                        .nickname(questionLikes1.getUsers().getNickname())
                        .likeYn(questionLikes1.isLikeYn())
                        .unlikeYn(questionLikes1.isUnlikeYn())
                        .build()
                ).collect(Collectors.toList());
    }

    default List<AnswerDto.LikeResponse> answerLikesToAnswerLikesResponseDto(List<AnswerLikes> answerLikes) {
        // 답변에 좋아요,싫어요를 체크한 유저 정보를 리스트로 저장
        return answerLikes.stream()
                .map(answerLikes1 -> AnswerDto.LikeResponse
                        .builder()
                        .userId(answerLikes1.getUsers().getId())
                        .nickname(answerLikes1.getUsers().getNickname())
                        .likeYn(answerLikes1.isLikeYn())
                        .unlikeYn(answerLikes1.isUnlikeYn())
                        .build()
                ).collect(Collectors.toList());
    }
}
