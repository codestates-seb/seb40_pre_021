package com.pre21.mapper;

import com.pre21.dto.QuestionCommentPostDto;
import com.pre21.entity.QuestionComments;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionCommentMapper {
}
