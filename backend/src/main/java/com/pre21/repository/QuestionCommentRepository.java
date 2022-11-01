package com.pre21.repository;

import com.pre21.entity.QuestionComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionCommentRepository extends JpaRepository<QuestionComments, Long> {
}
