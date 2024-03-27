package org.example.message.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ParticipantsDTO {

    private String roomId; // 채팅방 식별 아이디
    private String accountID; // 식별자 아이디
    private String userId; // 참여자 아이디
    private String userName; // 참여자 이름
    private Date joinedAt;

}
