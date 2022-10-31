package com.pre21.service;

import com.pre21.dto.QuestionDto;
import com.pre21.entity.QuestionLikes;
import com.pre21.entity.Questions;
import com.pre21.entity.User;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.QuestionLikeRepository;
import com.pre21.repository.QuestionsRepository;
import com.pre21.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final UserRepository userRepository;
    private final QuestionsRepository questionsRepository;
    private final QuestionLikeRepository questionLikeRepository;


    // UserLike를 DB에 저장하는 역살
    public void saveUserLike(Long questionId, QuestionDto.Like like) {
        Long userId = like.getUserId();
        User findUser = verifiedExistUser(userId);
        Questions findQuestion = verifiedExistQuestion(questionId);
        findQuestion.setVote(like.getCount());

        Optional<QuestionLikes> findQuestionLikes = questionLikeRepository
                .findQuestionLikesByUsers(findUser);

        if (findQuestionLikes.isPresent()) {
            QuestionLikes likes = findQuestionLikes.get();
            likes.setLikeYn(like.isLikeYn());
            likes.setUnlikeYn(like.isUnlikeYn());
            QuestionLikes savedLike = questionLikeRepository.save(likes);
            findQuestion.addQuestionsLikes(savedLike);
            findUser.addQuestionsLikes(savedLike);
        } else {
            QuestionLikes likes = new QuestionLikes(like.isLikeYn(), like.isUnlikeYn());
            likes.setUsers(findUser);
            likes.setQuestions(findQuestion);
            QuestionLikes savedLike = questionLikeRepository.save(likes);
            findQuestion.addQuestionsLikes(savedLike);
            findUser.addQuestionsLikes(savedLike);
        }
    }



    private User verifiedExistUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)
        );
    }

    private Questions verifiedExistQuestion(Long questionId) {
        return questionsRepository.findById(questionId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.TOKEN_NOT_FOUND)
        );
    }
}
