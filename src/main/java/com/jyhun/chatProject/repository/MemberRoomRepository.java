package com.jyhun.chatProject.repository;

import com.jyhun.chatProject.entity.MemberRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoomRepository extends JpaRepository<MemberRoom,Long> {
    MemberRoom findByMemberIdAndRoomId(Long memberId, Long roomId);
}
