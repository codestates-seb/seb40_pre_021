package com.pre21.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author dev32user
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionCommentMapper {
}
