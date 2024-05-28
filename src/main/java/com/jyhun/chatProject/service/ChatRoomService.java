package com.jyhun.chatProject.service;

import com.jyhun.chatProject.dto.ChatRoomDTO;
import com.jyhun.chatProject.entity.ChatRoom;
import com.jyhun.chatProject.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Transactional(readOnly = true)
    public List<ChatRoom> findChatRoomList(){
        return chatRoomRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ChatRoom findChatRoom(Long roomId){
        return chatRoomRepository.findById(roomId).orElse(null);
    }

    public void addChatRoom(ChatRoomDTO chatRoomDTO) {
        ChatRoom chatRoom = chatRoomDTO.toEntity();
        chatRoomRepository.save(chatRoom);
    }

}
