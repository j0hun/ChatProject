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
public class ChatRoom extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    private String roomName;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
    }

    public void changeMember(Member member) {
        this.member = member;
        if (member != null) {
            member.getChatRoomList().add(this);
        }
    }
}
