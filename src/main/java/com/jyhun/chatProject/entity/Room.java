package com.jyhun.chatProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String roomName;

    @OneToMany(mappedBy = "room")
    private List<Chat> chatList = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<MemberRoom> memberRoomList = new ArrayList<>();

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public void addRoom(MemberRoom memberRoom) {
        this.memberRoomList.add(memberRoom);
    }
}
