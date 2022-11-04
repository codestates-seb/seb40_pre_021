package com.pre21.mapper;

import com.pre21.dto.MyPageDto;
import com.pre21.entity.Tags;
import com.pre21.entity.UserTags;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    default List<MyPageDto.TagResponse> TagToTagResponse(List<UserTags> tagsList) {
        return tagsList.stream()
                .map(tag -> MyPageDto.TagResponse
                        .builder()
                        .tagId(tag.getCount())
                        .build()
                ).collect(Collectors.toList());
    }
}
