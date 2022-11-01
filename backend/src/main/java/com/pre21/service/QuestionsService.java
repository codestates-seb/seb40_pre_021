package com.pre21.service;

import com.pre21.dto.QuestionPatchDto;
import com.pre21.dto.QuestionsPostDto;
import com.pre21.entity.*;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionsService {
    private final QuestionsRepository questionsRepository;
    private final QuestionsTagsRepository questionsTagsRepository;
    private final TagsRepository tagsRepository;
    private final UserRepository userRepository;
    private final AnswersRepository answersRepository;
    private final AdoptionRepository adoptionRepository;


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
                        // questionsRepository.save(questions);
                        findUser.addQuestion(questions);
                        questions.addUser(findUser);

                    } else {
                        Tags tags1 = tagsRepository.findByTitle(e).orElseThrow(IllegalArgumentException::new);
                        updateTagCount(tags1);
                        QuestionsTags questionsTags = new QuestionsTags(questions, tags1);
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

        return (List<Questions>) questionsRepository.findAll();
    }

    public Page<Questions> findPageQuestions(int page, int size) {

        return questionsRepository.findAll(PageRequest.of(page, size,
                Sort.by("id").descending()));
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


    /**
     * @method : 질문 채택 여부 반영
     * @param questionId : 질문식별자
     * @param answerId : 유저식별자
     * @param userId : 유저식별자
     * @author mozzi327
     */
    public void adoptingQuestion(Long questionId,
                                 Long answerId,
                                 Long userId) {
        Questions findQuestion = verfiedQuestion(questionId);
        if (!Objects.equals(findQuestion.getUsers().getId(), userId))
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);
        if (findQuestion.isChooseYn()) throw new BusinessLogicException(ExceptionCode.ALREADY_ADOPTED);
        findQuestion.setChooseYn(true);
        questionsRepository.save(findQuestion);
        Answers findAnswer = verfiedAnswer(answerId);
        User findUser = verifiedUser(userId);
        Adoption adoption = new Adoption();
        adoption.setQuestions(findQuestion);
        adoption.setAnswers(findAnswer);
        adoption.setUsers(findUser);
        adoptionRepository.save(adoption);

    }


    /**
     * @method : question 정보 조회
     * @param questionId : 질문식별자
     * @return : Questions
     * @author : mozzi327
     */
    private Questions verfiedQuestion(Long questionId) {
        return questionsRepository
                .findById(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

    /**
     * @method : answer 정보 조회
     * @param answerId : 답변식별자
     * @return : Answers
     * @author : mozzi327
     */
    private Answers verfiedAnswer(Long answerId) {
        return answersRepository
                .findById(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }

    /**
     * @method : user 정보 조회
     * @param userId : 유저식별자
     * @return : User
     * @author : mozzi327
     */
    private User verifiedUser(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }
}
