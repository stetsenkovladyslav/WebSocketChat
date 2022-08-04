package com.example.chat.service.impl;

import com.example.chat.mapper.UserMapper;
import com.example.chat.model.UserEntity;
import com.example.chat.model.dto.UserDto;
import com.example.chat.repository.UserRepository;
import com.example.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Wrong username: " + username));
    }

    @Override
    public UserEntity save(UserDto userDto, SimpMessageHeaderAccessor headerAccessor) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(userDto.getUsername());
        UserEntity user = userOptional
                .orElseGet(() -> userRepository.save(UserMapper.toUserEntity(userDto)));
        headerAccessor.getSessionAttributes().put("username", user.getUsername());
        return user;
    }
}