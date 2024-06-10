package com.jyhun.chatProject.dto;

import com.jyhun.chatProject.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomResponseDTO {

    private Long id;

    private String roomName;

    private String memberName;

    public static RoomResponseDTO toDTO(Room room){
        return new RoomResponseDTO(room.getId(),room.getRoomName());
    }

    public RoomResponseDTO(Long id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }
}
