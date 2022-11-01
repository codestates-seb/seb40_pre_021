package com.pre21.service;

import com.pre21.dto.QuestionsPostDto;
import com.pre21.entity.*;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    // 질문 조회
    public Questions findQuestion(Long questionId) {
        Questions findQuestion = findVerifiedQuestion(questionId);

        return findQuestion;
    }

    // 질문 전체 조회
    public List<Questions> findQuestions() {
/* 2022.11.01
        List<Questions> findQuestions = questionsRepository.findAll();


        findQuestions.stream()
                .map(question -> {
                    Long id = question.getId();
                    questionsTagsRepository.findById()



                })
*/


        return (List<Questions>) questionsRepository.findAll();
    }

    // 질문 전체 개수
    public long findQuestionCount() {

        return questionsRepository.count();
    }

    // 질문 삭제
    public void deleteQuestion(long questionId) throws Exception {
        Questions findQuestion = findVerifiedQuestion(questionId);

        questionsRepository.delete(findQuestion);

    }

    public Questions findVerifiedQuestion(long questionId) {
        Optional<Questions> optionalQuestion =
                questionsRepository.findById(questionId);

        Questions findQuestion =
                optionalQuestion.orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

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
