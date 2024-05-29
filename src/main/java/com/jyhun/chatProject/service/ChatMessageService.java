package com.jyhun.chatProject.service;

import com.jyhun.chatProject.dto.ChatMessageDTO;
import com.jyhun.chatProject.entity.ChatMessage;
import com.jyhun.chatProject.entity.ChatRoom;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.repository.ChatMessageRepository;
import com.jyhun.chatProject.repository.ChatRoomRepository;
import com.jyhun.chatProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addChatMessage(ChatMessageDTO chatMessageDTO, Long chatRoomId, Long memberId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElse(null);
        Member member = memberRepository.findById(memberId).orElse(null);
        ChatMessage chatMessage = chatMessageDTO.toEntity();
        chatMessage.changeChatRoom(chatRoom);
        chatMessage.changeMember(member);
        chatMessageRepository.save(chatMessage);
    }

}
