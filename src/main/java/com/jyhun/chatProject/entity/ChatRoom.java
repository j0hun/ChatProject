package com.jyhun.chatProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue
    private Long id;

    private String roomName;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    public ChatRoom(String roomName) {
        this.roomName = roomName;
    }
}
