package com.jyhun.chatProject.dto;

import com.jyhun.chatProject.entity.Room;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequestDTO {

    private String roomName;

    public Room toEntity(){
        return new Room(roomName);
    }

}
