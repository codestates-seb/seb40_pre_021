package com.pre21.mapper;

import com.pre21.dto.AuthDto;
import com.pre21.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T04:46:59+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User joinToUserEntity(AuthDto.Join requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( requestBody.getEmail() );

        return user.build();
    }
}
