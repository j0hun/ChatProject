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
    @GeneratedValue
    private Long id;

    private String roomName;

    @OneToMany(mappedBy = "chatRoom")
    private List<Chat> chatList = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    private List<MemberRoom> memberRoomList = new ArrayList<>();

    public Room(String roomName) {
        this.roomName = roomName;
    }
}
