package com.jyhun.chatProject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Chat(String message) {
        this.message = message;
    }

    public void changeMember(Member member){
        this.member = member;
        if (member != null){
            member.getChatList().add(this);
        }
    }

    public void changeRoom(Room room){
        this.room = room;
        if (room != null){
            room.getChatList().add(this);
        }
    }

}