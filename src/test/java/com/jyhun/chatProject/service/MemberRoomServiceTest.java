package com.jyhun.chatProject.service;

import com.jyhun.chatProject.constant.Role;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.entity.Room;
import com.jyhun.chatProject.repository.MemberRepository;
import com.jyhun.chatProject.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberRoomServiceTest {

    @Autowired
    MemberRoomService memberRoomService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RoomRepository roomRepository;

    @Test
    void enter() {
        //given
        Member member = new Member("name","email","password", Role.USER);
        Room room = new Room("roomName");
        memberRepository.save(member);
        roomRepository.save(room);
        //when
        memberRoomService.enter(member.getId(),room.getId());
        //then
        System.out.println(member.getMemberRoomList());
        Assertions.assertThat(member.getMemberRoomList());
    }

}