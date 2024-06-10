package com.jyhun.chatProject.service;

import com.jyhun.chatProject.dto.RoomRequestDTO;
import com.jyhun.chatProject.dto.RoomResponseDTO;
import com.jyhun.chatProject.entity.Room;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.repository.RoomRepository;
import com.jyhun.chatProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<RoomResponseDTO> findRoomList() {
        List<Room> roomList = roomRepository.findAll();
        List<RoomResponseDTO> roomResponseDTOList = new ArrayList<>();
        for (Room room : roomList) {
            RoomResponseDTO roomResponseDTO = RoomResponseDTO.toDTO(room);
            Member findMember = memberRepository.findByRoomId(room.getId());
            roomResponseDTO.setMemberName(findMember.getName());
            roomResponseDTOList.add(roomResponseDTO);
        }
        return roomResponseDTOList;
    }

    @Transactional(readOnly = true)
    public RoomResponseDTO findRoom(Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        RoomResponseDTO roomResponseDTO = RoomResponseDTO.toDTO(room);
        Member findMember = memberRepository.findByRoomId(id);
        roomResponseDTO.setMemberName(findMember.getName());
        return roomResponseDTO;
    }

    public void addRoom(RoomRequestDTO roomRequestDTO, String email) {
        Room room = roomRequestDTO.toEntity();
        Member member = memberRepository.findByEmail(email).orElse(null);
        roomRepository.save(room);
    }


}
