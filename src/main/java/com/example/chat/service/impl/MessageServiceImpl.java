package com.example.chat.service.impl;

import com.example.chat.mapper.MessageMapper;
import com.example.chat.model.MessageEntity;
import com.example.chat.model.UserEntity;
import com.example.chat.model.dto.MessageDto;
import com.example.chat.repository.MessageRepository;
import com.example.chat.service.MessageService;
import com.example.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    @Override
    public MessageEntity save(MessageDto messageDto) {
        UserEntity user = userService.getByUsername(messageDto.getSender());
        return messageRepository.save(MessageMapper.toMessageEntity(messageDto, user));
    }

    @Override
    public List<MessageDto> findLatestMessages(Integer countOfMessages) {
        var messages = messageRepository.findAll(countOfMessages);
        List<MessageDto> chatHistory = messages
                .stream()
                .map(MessageMapper::toMessageDto)
                .collect(Collectors.toList());
        return chatHistory;
    }
}
