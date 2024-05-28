package com.jyhun.chatProject.repository;

import com.jyhun.chatProject.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {

    @Query("select cm from ChatMessage cm " +
            "where cm.chatRoom.id = :roomId")
    List<ChatMessage> findAllByRoomId(@Param("roomId") Long roomId);

}
