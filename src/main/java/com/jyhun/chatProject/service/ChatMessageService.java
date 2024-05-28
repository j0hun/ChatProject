package com.jyhun.chatProject.service;

import com.jyhun.chatProject.dto.ChatMessageDTO;
import com.jyhun.chatProject.entity.ChatMessage;
import com.jyhun.chatProject.entity.ChatRoom;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;

    @Transactional
    public void addChatMessage(ChatMessageDTO chatMessageDTO, Long chatRoomId, String email) {
        ChatRoom chatRoom = chatRoomService.findChatRoom(chatRoomId);
        Member member = memberService.findMemberByEmail(email);
        ChatMessage chatMessage = chatMessageDTO.toEntity();
        chatMessage.changeChatRoom(chatRoom);
        chatMessage.changeMember(member);
        chatMessageRepository.save(chatMessage);
    }

}
