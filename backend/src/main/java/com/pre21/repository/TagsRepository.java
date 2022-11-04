package com.pre21.repository;

import com.pre21.entity.QuestionsTags;
import com.pre21.entity.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagsRepository extends JpaRepository<Tags, Long> {
    Optional<Tags> findByTitle(String title);
}
