package com.pre21.service;

import com.pre21.entity.Questions;
import com.pre21.entity.Tags;
import com.pre21.repository.TagsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagsService {
    private final TagsRepository tagsRepository;

    public TagsService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

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
/*

    public Page<Tags> findPageTags(int page, int size) {
        return tagsRepository
                .findAll(PageRequest
                        .of(page, size, Sort.by("id").descending()));
    }

    public Page<Tags> findMyTags(Long userId, int page, int size) {
        return tagsRepository.findAllByUsersId(
                userId,
                PageRequest.of(page, size, Sort.by("id").descending()));
    }*/
}
