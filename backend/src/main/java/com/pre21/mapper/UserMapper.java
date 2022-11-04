package com.pre21.mapper;

import com.pre21.dto.AnswerDto;
import com.pre21.dto.AuthDto;
import com.pre21.dto.UserDto;
import com.pre21.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User joinToUserEntity(AuthDto.Join requestBody);
    default AuthDto.UserInfo userToUserResponse(User findUser) {
        return AuthDto.UserInfo
                .builder()
                .id(findUser.getId())
                .nickname(findUser.getNickname())
                .createdAt(findUser.getCreatedAt())
                .build();
    }
}
