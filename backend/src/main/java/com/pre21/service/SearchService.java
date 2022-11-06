package com.pre21.service;

import com.pre21.entity.Questions;
import com.pre21.entity.QuestionsTags;
import com.pre21.repository.QuestionsRepository;
import com.pre21.repository.QuestionsTagsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SearchService {

    private final QuestionsTagsRepository questionsTagsRepository;
    private final QuestionsRepository questionsRepository;


    /**
     * 키워드 리스트 리턴 메서드
     * @param keyword 사용자 질문
     * @return List(Question)
     * @author mozzi327
     */
    public List<Questions> findQuestionForKeyword(String keyword) {
        return new ArrayList<>(makeQuestionListByKeyword(keyword));
    }



    /**
     * 문자열 추출 메서드
     * @param keyword 사용자 질문
     * @return Set(Questions)
     * @author mozzi327
     */

    public Set<Questions> makeQuestionListByKeyword(String keyword) {

        // 문자를 %20(+기호)으로 구분하여 분리
        String[] keywordArr = keyword.split(" ");

        // tag 정보와 제목 문자열을 구분
        List<String> tags = new ArrayList<>();
        List<String> title = new ArrayList<>();

        // 태그 + 키워드 전체 문자열을 만들 StringBuilder 선언
        StringBuilder bufferKeyword = new StringBuilder();

        String word;
        for (int i = 0; i < keywordArr.length; i++) {
            word = keywordArr[i];
            if (word.startsWith("[")) { // 태그 정보 : 문자열을 추출하여 tag 리스트에 담음
                word = word
                        .replaceAll("\\[", "")
                        .replaceAll("]", "");
                tags.add(word);
                bufferKeyword.append(word).append(' '); // 문자열은 계속 합쳐준다
            } else { // 제목 문자열 : title 리스트에 담음
                title.add(word);
                bufferKeyword.append(word).append(' '); // 문자열은 계속 합쳐준다
            }
        }

        String totalKeyword = bufferKeyword.toString(); // 스트링 변환
        log.info("tags : {}", tags);
        log.info("titles : {}", title);

        // 끝 공백 제거
        totalKeyword = totalKeyword.substring(0, totalKeyword.length() - 1);

        // 중복 제거를 위해 Set 선언
        Set<Questions> findQuestion = new HashSet<>();

        // 태그에 대한 질문 모두 가져오기
        for (String tag : tags) {
            List<QuestionsTags> findQtags = questionsTagsRepository.findQtag(tag);
            findQtags.forEach(qtags -> findQuestion.addAll(questionsRepository.findAllByQuestionsTags(qtags)));

        }

        // 합쳐진 문자열이 포함된 질문 모두 가져오기
        findQuestion.addAll(questionsRepository.findByKeyword(totalKeyword));


        // 마지막으로 각 제목 문자열이 포함된 질문 모두 가져오기
        for (String t : title) {
            findQuestion.addAll(questionsRepository.findByKeyword(t));
        }

        // 중복 제거 리턴
        return findQuestion;
    }

}

