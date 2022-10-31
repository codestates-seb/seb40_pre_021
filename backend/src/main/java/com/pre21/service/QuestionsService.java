package com.pre21.service;

import com.pre21.dto.QuestionsPostDto;
import com.pre21.entity.*;
import com.pre21.repository.QuestionsRepository;
import com.pre21.repository.QuestionsTagsRepository;
import com.pre21.repository.TagsRepository;
import com.pre21.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionsService {
    private final QuestionsRepository questionsRepository;
    private final QuestionsTagsRepository questionsTagsRepository;
    private final TagsRepository tagsRepository;
    private final UserRepository userRepository;
    private final TagsService tagsService;


    // 질문 생성
    public void createQuestion(QuestionsPostDto questionsPostDto) throws Exception {
        Long userId = questionsPostDto.getUser().getId();
        User findUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("d"));


        Questions questions = new Questions(questionsPostDto.getTitle(), questionsPostDto.getContents());
        List<String> tags = questionsPostDto.getTags();

        tags.forEach(
                e -> {
                    if (tagsRepository.findByTitle(e).isEmpty()) {
                        Tags tags1 = tagsRepository.save(new Tags(e));
                        QuestionsTags questionsTags = new QuestionsTags(questions, tags1);
                        questionsTagsRepository.save(questionsTags);
                        questions.addQuestionsTags(questionsTags);
                        //questionsRepository.save(questions);
                        findUser.addQuestion(questions);
                        questions.addUser(findUser);

                    } else {
                        Tags tags1 = tagsRepository.findByTitle(e).orElseThrow(IllegalArgumentException::new);
                        updateTagCount(tags1);
                        QuestionsTags questionsTags = new QuestionsTags(questions,tags1);
                        questionsTagsRepository.save(questionsTags);
                        questions.addQuestionsTags(questionsTags);
                        findUser.addQuestion(questions);
                        questions.addUser(findUser);
                    }
                }
        );
    }

    //질문 삭제
    public void deleteQuestion(long questionId) throws Exception {
        Questions findQuestion = findVerifiedQuestion(questionId);

        questionsRepository.delete(findQuestion);

    }

    public Questions findVerifiedQuestion(long questionId) throws Exception {
        Optional<Questions> optionalQuestion =
                questionsRepository.findById(questionId);

        Questions findQuestion =
                optionalQuestion.orElseThrow(() -> new Exception("Question Not Found"));

        return findQuestion;
    }

    // 태그 수 업데이트
    private void updateTagCount(Tags tags) {

        int earnedTagCount = tags.getCount() + 1;
        tags.setCount(earnedTagCount);
        tags.setLatest(LocalDateTime.now());

        tagsRepository.save(tags);
    }
}
