package com.jyhun.chatProject.repository;

import com.jyhun.chatProject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByEmail(String email);

    @Query("select cr.member From ChatRoom cr where cr.id = :id")
    Member findByChatRoomId(Long id);

}
