package com.jyhun.chatProject.service;

import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.entity.MemberRoom;
import com.jyhun.chatProject.entity.Room;
import com.jyhun.chatProject.repository.MemberRepository;
import com.jyhun.chatProject.repository.MemberRoomRepository;
import com.jyhun.chatProject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberRoomService {

    private final MemberRoomRepository memberRoomRepository;
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    public void enter(Long memberId, Long roomId){
        Member member = memberRepository.findById(memberId).orElse(null);
        Room room = roomRepository.findById(roomId).orElse(null);
        MemberRoom memberRoom = new MemberRoom();
        memberRoom.enterRoom(member,room);
        memberRoomRepository.save(memberRoom);
    }

    public void leave(Long memberId, Long roomId){
        MemberRoom memberRoom = memberRoomRepository.findByMemberIdAndRoomId(memberId, roomId);
        Member member = memberRepository.findById(memberId).orElse(null);
        memberRoom.leaveRoom(member);
    }

}

