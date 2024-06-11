package com.jyhun.chatProject.repository;

import com.jyhun.chatProject.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Long> {

    @Query("select c from Chat c where c.room.id = :roomId " +
            "and :date <= c.date")
    List<Chat> findByRoomIdAndDate(@Param("roomId") Long roomId, @Param("date") LocalDateTime date);
}
