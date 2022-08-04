package com.example.chat.controller;

import com.example.chat.mapper.MessageMapper;
import com.example.chat.model.MessageEntity;
import com.example.chat.model.UserEntity;
import com.example.chat.model.dto.MessageDto;
import com.example.chat.model.dto.UserDto;
import com.example.chat.model.enums.MessageType;
import com.example.chat.service.MessageService;
import com.example.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final UserService userService;
    private final MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public MessageDto sendMessage(@Payload MessageDto message) {
        return MessageMapper.toMessageDto(messageService.save(message));
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public MessageDto addUser(@Payload UserDto userDto,
                              SimpMessageHeaderAccessor headerAccessor) throws Exception {
        UserEntity userEntity = userService.save(userDto, headerAccessor);
        return new MessageDto(MessageType.JOIN, userEntity.getUsername() + " joined!",
                userEntity.getUsername());
    }

    @GetMapping(path = "/history")
    @SendTo("/topic/public")
    public List<MessageEntity> chatHistory(Pageable pageable) {
        return messageService.findLatestMessages(50);
    }
}