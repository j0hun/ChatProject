package com.jyhun.chatProject.service;

import com.jyhun.chatProject.dto.ChatDTO;
import com.jyhun.chatProject.entity.Chat;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.entity.Room;
import com.jyhun.chatProject.repository.ChatRepository;
import com.jyhun.chatProject.repository.MemberRepository;
import com.jyhun.chatProject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final ChatRepository chatRepository;
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addChat(ChatDTO chatDTO, Long roomId, Long memberId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        Member member = memberRepository.findById(memberId).orElse(null);
        Chat chat = chatDTO.toEntity();
        chat.changeRoom(room);
        chat.changeMember(member);
        chatRepository.save(chat);
    }

    @Transactional(readOnly = true)
    public List<ChatDTO> findChatListByRoomIdAndDate(Long roomId, LocalDateTime date) {
        List<Chat> chatList = chatRepository.findByRoomIdAndDate(roomId,date);
        List<ChatDTO> chatDTOList = new ArrayList<>();
        for (Chat chat : chatList) {
            ChatDTO chatDTO = ChatDTO.toDTO(chat);
            chatDTOList.add(chatDTO);
        }
        return chatDTOList;
    }
}
