package com.example.chat.repository;

import com.example.chat.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    @Query(nativeQuery = true, value = "WITH m AS " +
            "(SELECT * FROM messages m ORDER BY m.id DESC LIMIT ?1) " +
            "SELECT * FROM m ORDER BY id ASC")
    List<MessageEntity> findAll(int limit);
}