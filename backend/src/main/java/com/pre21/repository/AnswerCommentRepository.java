package com.pre21.repository;

import com.pre21.entity.AnswerComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerCommentRepository extends JpaRepository<AnswerComments, Long> {
}
