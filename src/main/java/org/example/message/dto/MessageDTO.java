package org.example.message.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {

    private String messageId; // 메세지 식별 아이디
    private String roomId; // 채팅방 식별 아이디
    private String senderId; // 보낸사람
    private String messageText; // 메세지 내용
    private Date sentAt; // 메세지 보낸 시간

}
