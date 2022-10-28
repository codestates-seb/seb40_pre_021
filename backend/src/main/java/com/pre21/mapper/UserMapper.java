package com.pre21.mapper;

import com.pre21.dto.UserDto;
import com.pre21.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User joinToUserEntity(UserDto.Join requestBody);
}
