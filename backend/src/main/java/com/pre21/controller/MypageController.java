package com.pre21.controller;


import com.pre21.entity.Answers;
import com.pre21.entity.Questions;
import com.pre21.entity.Tags;
import com.pre21.entity.User;
import com.pre21.mapper.AnswersMapper;
import com.pre21.mapper.QuestionsMapper;
import com.pre21.mapper.UserMapper;
import com.pre21.service.AnswersService;
import com.pre21.service.QuestionsService;
import com.pre21.service.TagsService;
import com.pre21.service.UserService;
import com.pre21.util.dto.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    private final int page = 0;
//    private final int size = 5;
//    private final UserService userService;
//    private final QuestionsService questionsService;
//    private final AnswersService answersService;
//    private final TagsService tagsService;
//    private final UserMapper userMapper;
//
//    private final AnswersMapper answersMapper;
//    private final QuestionsMapper questionsMapper;
//
//    /**
//     * 마이페이지 api 명세서 중 유저 정보에 해당하는 요청사항 컨트롤러입니다.
//     *
//     * @param userId 쿠키에서 읽어온 접속 중인 사용자의 Id 값입니다.
//     * @author dev32user
//     */
//    @GetMapping("/user-info")
//    public ResponseEntity getUserInfo(@CookieValue(name = "userId") Long userId) {
//        User findUser = userService.findUser(userId);
//
//        return new ResponseEntity<>(userMapper.userToUserResponse(findUser), HttpStatus.OK);
//    }
//
//    @GetMapping("/answer")
//    public ResponseEntity getAnswerInfo(@CookieValue(name = "userId") Long userId) {
//        Page<Answers> answersPage = answersService.findMyAnswers(userId, page, size);
//        List<Answers> answers = answersPage.getContent();
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(answersMapper.answerToAnswerResponse(answers), answersPage),
//                HttpStatus.OK);
//    }
//
//    @GetMapping("/question")
//    public ResponseEntity getQuestionInfo(@CookieValue(name = "userId") Long userId) {
//        Page<Questions> questionsPage = questionsService.findMyQuestions(userId, page, size);
//        List<Questions> questions = questionsPage.getContent();
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(
//                        questionsMapper.questionsToQuestionResponses(questions), questionsPage),
//                HttpStatus.OK);
//    }
///*
//
//    @GetMapping("/tags")
//    public ResponseEntity getTagsInfo(@CookieValue(name = "userId") Long userId) {
//        Page<Tags> tagsPage = tagsService.findMyTags(userId, page, size);
//        List<Tags> tags = tagsPage.getContent();
//        return
//    }
//
//    */
}
