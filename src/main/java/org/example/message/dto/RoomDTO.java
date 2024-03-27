package org.example.message.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RoomDTO {

    private String roomId; // 채팅방 식별 아이디
    private String roomName; // 채팅방 이름
    private String roomMetaName; // 채팅방 이름
    private Date createdAt; // 채팅방 생성일자

}
