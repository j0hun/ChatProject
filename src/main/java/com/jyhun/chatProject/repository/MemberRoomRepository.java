package com.jyhun.chatProject.repository;

import com.jyhun.chatProject.entity.MemberRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRoomRepository extends JpaRepository<MemberRoom,Long> {
    Optional<MemberRoom> findByMemberIdAndRoomId(Long memberId, Long roomId);

    List<MemberRoom> findByRoomId(Long roomId);
}
