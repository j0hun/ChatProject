package com.jyhun.chatProject.repository;

import com.jyhun.chatProject.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {

    List<ChatMessage> findAllByRoomId(Long roomId);

}
