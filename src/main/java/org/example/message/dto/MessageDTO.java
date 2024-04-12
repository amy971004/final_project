package org.example.message.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {

    private String messageId; // 메세지 식별 아이디
    private String roomId; // 채팅방 식별 아이디
    private String senderId; // 보낸 사람의 식별 아이디
    private String senderUserId; // 보낸 사람의 사용자 ID
    private String senderUserName; // 보낸 사람의 이름
    private String messageText; // 메세지 내용
    private Date sentAt; // 메세지 보낸 시간
    private String readStatus; // 메세지 읽음 여부

}
