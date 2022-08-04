package com.example.chat.mapper;


import com.example.chat.model.MessageEntity;
import com.example.chat.model.UserEntity;
import com.example.chat.model.dto.MessageDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageMapper {

    public static MessageDto toMessageDto(MessageEntity messageEntity) {
        return new MessageDto(messageEntity.getMessageType(), messageEntity.getText(),
                messageEntity.getAuthor().getUsername());
    }

    public static MessageEntity toMessageEntity(MessageDto messageDto, UserEntity userEntity) {
        return new MessageEntity(messageDto.getContent(), messageDto.getType(), userEntity);
    }
}
