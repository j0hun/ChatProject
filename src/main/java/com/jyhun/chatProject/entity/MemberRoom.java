package com.jyhun.chatProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public MemberRoom(Member member, Room room) {
        this.member = member;
        this.room = room;
    }

    public void enterRoom(Member member, Room room) {
        this.member = member;
        member.getMemberRoomList().add(this);
        this.room = room;
        room.getMemberRoomList().add(this);
    }

    public void leaveRoom(Member member) {
        member.getMemberRoomList().remove(this);
    }
}
