package org.example.message.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RoomDTO {

    private String roomId; // 채팅방 식별 아이디
    private String roomName; // 채팅방 이름
    private String roomMetaName; // 채팅방 이름
    private String roomReceiverName; // 수신자 이름
    private Date createdAt; // 채팅방 생성일자

    // 추가된 필드
    private String lastMessage; // 마지막 메시지 내용
    private String formattedLastMessageDate;
    private String opponentName; // 상대방 이름


}
