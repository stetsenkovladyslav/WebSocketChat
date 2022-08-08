package com.example.chat.controller;

import com.example.chat.mapper.MessageMapper;
import com.example.chat.model.dto.MessageDto;
import com.example.chat.model.dto.UserDto;
import com.example.chat.service.MessageService;
import com.example.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final UserService userService;
    private final MessageService messageService;

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public List<MessageDto> login(@Payload UserDto userDto, SimpMessageHeaderAccessor headerAccessor) {
        userService.save(userDto, headerAccessor);
        return messageService.findLatestMessages(50);
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public MessageDto sendMessage(@Payload MessageDto message) {
        return MessageMapper.toMessageDto(messageService.save(message));
    }


}