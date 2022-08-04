package com.example.chat.mapper;

import com.example.chat.model.UserEntity;
import com.example.chat.model.dto.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static UserEntity toUserEntity(UserDto userDto) {
        return new UserEntity(userDto.getUsername());
    }
}