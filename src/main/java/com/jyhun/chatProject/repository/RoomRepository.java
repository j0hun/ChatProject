package com.jyhun.chatProject.repository;

import com.jyhun.chatProject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
