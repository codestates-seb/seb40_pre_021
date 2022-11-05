package com.pre21.controller;


import com.pre21.dto.MyPageDto;
import com.pre21.entity.*;
import com.pre21.mapper.*;
import com.pre21.service.*;
import com.pre21.util.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 마이페이지 Controller 입니다.
 *
 * @author dev32user
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class MypageController {
    private final int page = 0;
    private final int size = 5;
    private final AuthService authService;
    private final QuestionsService questionsService;
    private final AnswersService answersService;
    private final TagsService tagsService;
    private final BookmarkService bookmarkService;
    private final UserMapper userMapper;

    private final AnswersMapper answersMapper;
    private final QuestionsMapper questionsMapper;
    private final TagMapper tagMapper;
    private final BookmarkMapper bookmarkMapper;

    /**
     * 마이페이지 api 명세서 중 유저 정보에 해당하는 요청사항 컨트롤러
     *
     * @param userId 사용자 식별자
     * @return ResponseEntity
     * @author dev32user
     */
    @GetMapping("/info")
    public ResponseEntity getUserInfo(@CookieValue(name = "userId") Long userId) {
        User findUser = authService.findUser(userId);
        return new ResponseEntity<>(userMapper.userToUserResponse(findUser), HttpStatus.OK);
    }

    /**
     * 마이페이지 api 명세서 중 질문 정보 조회에 해당하는 요청사항 컨트롤러
     *
     * @param userId 사용자 식별자
     * @author dev32user
     */
    @GetMapping("/questions")
    public ResponseEntity getQuestionInfo(@CookieValue(name = "userId") Long userId) {
        Page<Questions> questionsPage = questionsService.findMyQuestions(userId, page, size);
        List<Questions> questions = questionsPage.getContent();
        return new ResponseEntity<>(
                questionsMapper.questionsToQuestionResponses(questions),
                HttpStatus.OK);
    }

    /**
     * 마이페이지 api 명세서 중 답변 정보 조회에 해당하는 요청사항 컨트롤러
     *
     * @param userId 사용자 식별자
     * @author dev32user
     */
    @GetMapping("/answers")
    public ResponseEntity getAnswerInfo(@CookieValue(name = "userId") Long userId) {
        Page<Answers> answersPage = answersService.findMyAnswers(userId, page, size);
        List<Answers> answers = answersPage.getContent();
        return new ResponseEntity<>(answersMapper.answersToAnswerResponses(answers),
                HttpStatus.OK);
    }



    /**
     * 마이페이지 api 명세서 중 태그 정보 조회에 해당하는 요청사항 컨트롤러
     *
     * @param userId 사용자 식별자
     * @return ResponseEntity(MyPageDto.TagResponse)
     * @author mozzi327
     */
    @GetMapping("/tags")
    public ResponseEntity getTagsInfo(@CookieValue(name = "userId") Long userId) {
        List<UserTags> tags = tagsService.findMyTags(userId);
        List<MyPageDto.TagInfo> response = tagMapper.TagToTagResponse(tags);
        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK);
    }


    /**
     * 마이페이지 api 명세서 중 북마크 정보 조회에 해당하는 요청사항 컨트롤러
     *
     * @param userId 사용자 식별자
     * @return ResponseEntity(MypageDto.BookmarkResponse)
     * @author mozzi327
     */
    @GetMapping("/bookmarks")
    public ResponseEntity getBookmarkInfo(@CookieValue(name = "userId") Long userId) {
        List<Bookmark> bookmarks = bookmarkService.findMyBookmarks(userId);
        List<MyPageDto.BookmarkInfo> response = bookmarkMapper
                .bookmarkToBookmarkResponses(bookmarks);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }
}
