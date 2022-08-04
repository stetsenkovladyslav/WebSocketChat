package com.example.chat.model;

import com.example.chat.model.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class MessageEntity extends BaseEntity {

    @Column(name = "text", length = 1000, nullable = false)
    private String text;

    @Enumerated
    @Column(name = "message_type")
    private MessageType messageType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;
}