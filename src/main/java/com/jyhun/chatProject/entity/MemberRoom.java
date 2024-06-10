package com.jyhun.chatProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public void enterRoom(Member member, Room room) {
        member.addMember(this);
        room.addRoom(this);
    }

    public void leaveRoom(Member member) {
        this.member = member;
        member.removeMember(this);
    }
}
