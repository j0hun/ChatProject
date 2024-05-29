package com.jyhun.chatProject.service;

import com.jyhun.chatProject.dto.ChatRoomDTO;
import com.jyhun.chatProject.entity.ChatRoom;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.repository.ChatRoomRepository;
import com.jyhun.chatProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<ChatRoom> findChatRoomList(){
        return chatRoomRepository.findAll();
    }

    public void addChatRoom(ChatRoomDTO chatRoomDTO, String email) {
        ChatRoom chatRoom = chatRoomDTO.toEntity();
        Member member = memberRepository.findByEmail(email);
        chatRoom.changeMember(member);
        chatRoomRepository.save(chatRoom);
    }

}
