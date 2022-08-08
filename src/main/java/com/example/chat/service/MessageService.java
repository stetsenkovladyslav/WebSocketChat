package com.example.chat.service;

import com.example.chat.model.MessageEntity;
import com.example.chat.model.dto.MessageDto;

import java.util.List;

public interface MessageService {

    MessageEntity save(MessageDto messageDto);

    List<MessageDto> findLatestMessages(Integer countOfMessages);
}