package com.jyhun.chatProject.service;

import com.jyhun.chatProject.dto.RoomRequestDTO;
import com.jyhun.chatProject.dto.RoomResponseDTO;
import com.jyhun.chatProject.entity.MemberRoom;
import com.jyhun.chatProject.entity.Room;
import com.jyhun.chatProject.entity.Member;
import com.jyhun.chatProject.repository.MemberRoomRepository;
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
    private final MemberRoomRepository memberRoomRepository;

    @Transactional(readOnly = true)
    public List<RoomResponseDTO> findRoomList() {
        List<Room> roomList = roomRepository.findAll();
        List<RoomResponseDTO> roomResponseDTOList = new ArrayList<>();
        for (Room room : roomList) {
            RoomResponseDTO roomResponseDTO = RoomResponseDTO.toDTO(room);
            List<MemberRoom> memberRoomList = memberRoomRepository.findByRoomId(room.getId());
            MemberRoom memberRoom = memberRoomList.get(0);
            Member member = memberRoom.getMember();
            roomResponseDTO.setMemberName(member.getName());
            roomResponseDTOList.add(roomResponseDTO);
        }
        return roomResponseDTOList;
    }

    @Transactional(readOnly = true)
    public RoomResponseDTO findRoom(Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        RoomResponseDTO roomResponseDTO = RoomResponseDTO.toDTO(room);
        List<MemberRoom> memberRoomList = memberRoomRepository.findByRoomId(id);
        MemberRoom memberRoom = memberRoomList.get(0);
        Member member = memberRoom.getMember();
        roomResponseDTO.setMemberName(member.getName());
        return roomResponseDTO;
    }

    public void addRoom(RoomRequestDTO roomRequestDTO, String email) {
        Room room = roomRequestDTO.toEntity();
        Member member = memberRepository.findByEmail(email).orElse(null);
        MemberRoom memberRoom = new MemberRoom(member,room);
        roomRepository.save(room);
        memberRoomRepository.save(memberRoom);
    }


}
