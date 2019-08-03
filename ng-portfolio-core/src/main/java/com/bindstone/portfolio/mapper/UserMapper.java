package com.bindstone.portfolio.mapper;

import com.bindstone.portfolio.entity.User;
import com.bindstone.portfolio.mapper.dto.UserDto;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

/**
 * JPA/DTO Mapper for USER
 */
@Component
public class UserMapper implements DtoMapper<User, UserDto> {

    /**
     * JPA to DTO Mapper
     *
     * @param object JPA Object
     * @return DTO Object
     */
    @Override
    public UserDto toDto(User object) {
        Validate.notNull(object);
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .email(object.getEmail())
                .build();
    }

    /**
     * JPA to DTO Mapper
     *
     * @param objectDto DTO Object
     * @return JPA Object
     */
    @Override
    public User fromDto(UserDto objectDto) {
        Validate.notNull(objectDto);
        return User.builder()
                .id(objectDto.getId())
                .name(objectDto.getName())
                .email(objectDto.getEmail())
                .build();
    }
}
