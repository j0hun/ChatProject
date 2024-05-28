package com.jyhun.chatProject.service;

import com.jyhun.chatProject.entity.ChatMessage;
import com.jyhun.chatProject.entity.ChatRoom;
import com.jyhun.chatProject.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    @Transactional(readOnly = true)
    public List<ChatMessage> findChatMessageList(Long roomId){
        ChatRoom chatRoom = chatRoomService.findChatRoom(roomId);
        List<ChatMessage> chatMessageList = chatMessageRepository.findAllByRoomId(chatRoom.getId());
        return chatMessageList;
    }

    @Transactional
    public void addChatMessage(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }

}
