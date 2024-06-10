package com.jyhun.chatProject.repository;

import com.jyhun.chatProject.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
