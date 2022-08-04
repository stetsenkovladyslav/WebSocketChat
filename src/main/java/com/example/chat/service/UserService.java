package com.example.chat.service;

import com.example.chat.model.UserEntity;
import com.example.chat.model.dto.UserDto;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface UserService {

    UserEntity getByUsername(String username);

    UserEntity save(UserDto userDto, SimpMessageHeaderAccessor headerAccessor);
}