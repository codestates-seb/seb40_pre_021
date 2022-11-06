package com.pre21.service;

import com.pre21.dto.QuestionDto;
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
    private final AuthRepository authRepository;
    private final AnswersRepository answersRepository;
    private final AdoptionRepository adoptionRepository;
    private final BookmarkRepository bookmarkRepository;
    private final QuestionCommentRepository questionCommentRepository;

    private final UserTagRepository userTagRepository;


    // 질문 생성
    public Long createQuestion(QuestionDto.Post post,
                               Long userId) {
        User findUser = authRepository.findById(userId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER));

        Questions questions = new Questions(post.getTitle(), post.getContents());
        List<String> tags = post.getTags();

        tags.forEach(
                e -> {
                    if (tagsRepository.findByTitle(e).isEmpty()) {
                        Tags tags1 = tagsRepository.save(new Tags(e));
                        QuestionsTags questionsTags = new QuestionsTags(questions, e, tags1);
                        UserTags userTags = new UserTags(findUser, tags1);
                        userTags.setTagCount(userTags.getTagCount() + 1);
                        questionsTagsRepository.save(questionsTags);
                        questions.addQuestionsTags(questionsTags);
                        findUser.addQuestion(questions);
                        questions.addUser(findUser);
                        findUser.addUserTags(userTags);

                    } else {
                        Tags tags1 = tagsRepository.findByTitle(e).orElseThrow(IllegalArgumentException::new);
                        updateTagCountUp(tags1);
                        QuestionsTags questionsTags = new QuestionsTags(questions, e, tags1);
                        UserTags userTags = userTagRepository.findByTagsAndUsers(tags1, findUser).orElse(
                                new UserTags(findUser, tags1)
                        );
                        userTags.setTagCount(userTags.getTagCount() + 1);
                        questionsTagsRepository.save(questionsTags);
                        questions.addQuestionsTags(questionsTags);
                        findUser.addQuestion(questions);
                        questions.addUser(findUser);
                        findUser.addUserTags(userTags);
                    }
                }
        );

        return questions.getId();
    }

    // 질문 조회
    public Questions findQuestion(Long questionId) {
        Questions findQuestion = verifiedExistQuestion(questionId);

        findQuestion.setViews(findQuestion.getViews() + 1);
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

    // 질문 전체 개수 출력
    public long findQuestionCount() {

        return questionsRepository.count();
    }

    // 질문 삭제
    public void deleteQuestion(Long questionId, Long userId) {

        Questions findQuestion = verifiedExistQuestion(questionId);

        if (!Objects.equals(findQuestion.getUsers().getId(), userId))
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);

        questionsRepository.delete(findQuestion);
    }

    // 태그 수 업데이트
    private void updateTagCountUp(Tags tags) {

        int earnedTagCount = tags.getCount() + 1;
        tags.setCount(earnedTagCount);
        tags.setLatest(LocalDateTime.now());

        tagsRepository.save(tags);
    }

    private void updateTagCountDown(Tags tags) {
        int nowTagCount = tags.getCount();

        if(nowTagCount == 1) {
            tagsRepository.delete(tags);
        } else {
            int earnedTagCount = nowTagCount - 1;
            tags.setCount(earnedTagCount);

            tagsRepository.save(tags);
        }
    }


    /**
     * @param questionId : 질문식별자
     * @param answerId   : 유저식별자
     * @param userId     : 유저식별자
     * @method : 질문 채택 여부 반영
     * @author mozzi327
     */
    public void adoptingQuestion(Long questionId,
                                 Long answerId,
                                 Long userId) {
        Questions findQuestion = verifiedExistQuestion(questionId);
        if (!Objects.equals(findQuestion.getUsers().getId(), userId))
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);
        if (findQuestion.isChooseYn()) throw new BusinessLogicException(ExceptionCode.ALREADY_ADOPTED);
        findQuestion.setChooseYn(true);
        questionsRepository.save(findQuestion);
        Answers findAnswer = verifiedExistAnswer(answerId);
        findAnswer.setChooseYn(true);
        //answersRepository.save(findAnswer);
        User findUser = verifiedExistUser(userId);
        Adoption adoption = new Adoption();
        adoption.setQuestions(findQuestion);
        adoption.setAnswers(findAnswer);
        adoption.setUsers(findUser);
        adoptionRepository.save(adoption);

    }


    /**
     * 질문 patch 요청에 대한 서비스 메서드입니다.
     *
     * @param userId           Long 타입 사용자 Id 값입니다.
     * @param questionId       Long 타입 Question Id 값입니다.
     * @param patch QuestionPatchDto 요청입니다.
     * @author dev32user
     */
    public Questions patchQuestion(Long userId, Long questionId, QuestionDto.Patch patch) {
        if (!Objects.equals(userId, verifiedExistQuestion(questionId).getUsers().getId())) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);
        }

        Optional<Questions> optionalQuestion = questionsRepository.findById(questionId);
        User findUser = authRepository
                .findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        Questions updatedQuestion =
                optionalQuestion
                        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        updatedQuestion.setTitle(patch.getTitle());
        updatedQuestion.setContents(patch.getContents());
        updatedQuestion.setModifiedAt(LocalDateTime.now());

        List<String> tags = patch.getTags();

        questionsTagsRepository.deleteAllByQuestions(updatedQuestion);

        tags.forEach(
                e -> {
                    if (tagsRepository.findByTitle(e).isEmpty()) {
                        Tags tags1 = tagsRepository.save(new Tags(e));
                        QuestionsTags questionsTags = new QuestionsTags(updatedQuestion, e, tags1);
                        questionsTagsRepository.save(questionsTags);
                        updatedQuestion.addQuestionsTags(questionsTags);
                        findUser.addQuestion(updatedQuestion);
                        updatedQuestion.addUser(findUser);

                    } else {
                        Tags tags1 = tagsRepository.findByTitle(e).orElseThrow(IllegalArgumentException::new);
                        updateTagCountUp(tags1);
                        QuestionsTags questionsTags = new QuestionsTags(updatedQuestion, e, tags1);
                        questionsTagsRepository.save(questionsTags);
                        updatedQuestion.addQuestionsTags(questionsTags);
                        findUser.addQuestion(updatedQuestion);
                        updatedQuestion.addUser(findUser);
                    }
                }
        );

        return questionsRepository.save(updatedQuestion);
    }

    public void addQuestionBookmark(Long questionId, Long userId) {
        User findUser = verifiedExistUser(userId);
        Questions findQuestion = verifiedExistQuestion(questionId);
        Optional<Bookmark> findBookmark = bookmarkRepository.findBookmarksByUsersAndQuestions(findUser, findQuestion);

        if (findBookmark.isPresent()) {
            bookmarkRepository.delete(findBookmark.get());
        } else {
            Bookmark bookmark = new Bookmark(questionId);
            bookmark.setQuestions(findQuestion);
            bookmark.setUsers(findUser);
            Bookmark savedBookmark = bookmarkRepository.save(bookmark);
            findUser.addBookmark(savedBookmark);
            findQuestion.addBookmark(savedBookmark);
        }
    }

    public void addAnswerBookmark(Long questionId,  Long answerId, Long userId) {
        User findUser = verifiedExistUser(userId);
        Questions findQuestion = verifiedExistQuestion(questionId);
        Answers findAnswer = verifiedExistAnswer(answerId);
        Optional<Bookmark> findBookmark = bookmarkRepository.findBookmarksByUsersAndQuestionsAndAnswers(findUser, findQuestion, findAnswer);

        if (findBookmark.isPresent()) {
            bookmarkRepository.delete(findBookmark.get());
        } else {
            Bookmark bookmark = new Bookmark(questionId);
            bookmark.setQuestions(findQuestion);
            bookmark.setUsers(findUser);
            bookmark.setAnswers(findAnswer);
            bookmarkRepository.save(bookmark);
        }
    }


    /**
     * 질문에 대한 댓글을 생성하는 메서드입니다.
     * QuestionCommentRepository에 입력받은 questionCommentPostDto를 저장합니다.
     *
     * @param commentPost 댓글을 생성하는 요청의 RequestBody에 해당합니다.
     * @param questionId             댓글을 생성하는 질문의 Id입니다.
     * @author dev32user
     */
    public void createQuestionComment(QuestionDto.CommentPost commentPost,Long userId ,Long questionId) throws Exception {
        User findUser = authRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("findUser.findById 실패"));
        Questions questions = questionsRepository
                .findQuestionsById(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        QuestionComments questionComments = new QuestionComments(commentPost.getComments());
        questionComments.setQuestions(questions);
        questionComments.setUser(findUser);
        questionComments.setNickname(findUser.getNickname()); //2022.11.02 답변 작성 유저 닉네임 추가
        questionCommentRepository.save(questionComments);
    }


    /**
     * @method 유저 조회
     * @param userId 유저식별자
     * @return User
     * @author mozzi327
     */
    private User verifiedExistUser(Long userId) {
        return authRepository.findById(userId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)
        );
    }


    /**
     * @method 질문 조회
     * @param questionId 질문식별자
     * @return Questions
     * @author mozzi327
     */
    private Questions verifiedExistQuestion(Long questionId) {
        return questionsRepository.findById(questionId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND)
        );
    }


    /**
     * @method 답변 조회
     * @param answerId 답변식별자
     * @return Answers
     * @author mozzi327
     */
    private Answers verifiedExistAnswer(Long answerId) {
        return answersRepository.findById(answerId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND)
        );
    }



    public Page<Questions> findMyQuestions(Long userId, int page, int size) {
        return questionsRepository.findAllByUsersId(
                userId,
                PageRequest.of(page, size, Sort.by("id").descending()));
    }
}
