package com.pre21.service;

import com.pre21.entity.Questions;
import com.pre21.entity.Tags;
import com.pre21.repository.TagsRepository;
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

/*
    // 테그명 존재 여부 확인
    public void verifiedExistsTagTitle(Tags tags) throws Exception {
        Optional<Tags> tag = tagsRepository.findByTitle(tags.getTitle());
        if(tag.isPresent()) {
            updateTag(tags);
        } else {
            createTag(tags);
        }
        //Optional<Tags> findTags = tagsRepository.findByTitle(tags.getTitle());

        //return findTags.orElseThrow(() -> new Exception("Tag Not Found"));
    }*/
}
