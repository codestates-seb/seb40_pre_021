package com.pre21.service;

import com.pre21.entity.Questions;
import com.pre21.entity.Tags;
import com.pre21.entity.User;
import com.pre21.entity.UserTags;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.AuthRepository;
import com.pre21.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagsService {
    private final TagsRepository tagsRepository;
    private final AuthRepository authRepository;

    // 태그 생성
    public Tags createTag(Tags tags) {

        Tags savedTag = tagsRepository.save(tags);

        return savedTag;
    }

    // 태그 업데이트
    public Tags updateTag(String title) throws Exception {

        return null;
    }


    // 테그명 존재 여부 확인
    public Tags verifiedExistsTagTitle(String title) throws Exception {
        Optional<Tags> optionalTags = tagsRepository.findByTitle(title);

        Tags findTags =
                optionalTags.orElseThrow(() -> new Exception("Tag Not Found"));

        return findTags;
    }

    public Page<Tags> findPageTags(int page, int size) {
        return tagsRepository
                .findAll(PageRequest
                        .of(page, size, Sort.by("id").descending()));
    }

    public List<UserTags> findMyTags(Long userId) {
        User findUser = findIfExistUser(userId);
        return findUser.getUserTags();
    }

    private User findIfExistUser(Long userId) {
        return authRepository.findById(userId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }
}
