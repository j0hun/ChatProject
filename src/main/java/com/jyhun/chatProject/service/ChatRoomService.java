package com.jyhun.chatProject.service;

import com.jyhun.chatProject.dto.ChatRoomRequestDTO;
import com.jyhun.chatProject.dto.ChatRoomResponseDTO;
import com.jyhun.chatProject.entity.ChatRoom;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.repository.ChatRoomRepository;
import com.jyhun.chatProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<ChatRoomResponseDTO> findChatRoomList() {
        List<ChatRoom> chatRoomList = chatRoomRepository.findAll();
        List<ChatRoomResponseDTO> chatRoomResponseDTOList = new ArrayList<>();
        for (ChatRoom chatRoom : chatRoomList) {
            ChatRoomResponseDTO chatRoomResponseDTO = ChatRoomResponseDTO.toDTO(chatRoom);
            Member findMember = memberRepository.findByChatRoomId(chatRoom.getId());
            chatRoomResponseDTO.setMemberName(findMember.getName());
            chatRoomResponseDTOList.add(chatRoomResponseDTO);
        }
        return chatRoomResponseDTOList;
    }

    @Transactional(readOnly = true)
    public ChatRoomResponseDTO findChatRoom(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElse(null);
        ChatRoomResponseDTO chatRoomResponseDTO = ChatRoomResponseDTO.toDTO(chatRoom);
        Member findMember = memberRepository.findByChatRoomId(id);
        chatRoomResponseDTO.setMemberName(findMember.getName());
        return chatRoomResponseDTO;
    }

    public void addChatRoom(ChatRoomRequestDTO chatRoomRequestDTO, String email) {
        ChatRoom chatRoom = chatRoomRequestDTO.toEntity();
        Member member = memberRepository.findByEmail(email).orElse(null);
        chatRoom.changeMember(member);
        chatRoomRepository.save(chatRoom);
    }


}
