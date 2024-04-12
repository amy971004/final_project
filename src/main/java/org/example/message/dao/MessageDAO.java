package org.example.message.dao;

import org.example.message.dto.MessageDTO;

import java.util.List;

public interface MessageDAO {
    // 메세지 저장
    void saveMessage(MessageDTO messageDTO);

    // roomId로 해당 채팅방의 모든 참가자 accountId 배열로 반환
    List<String> getParticipantsByRoomId(String roomId);

    // roomId 로 모든 message 가져오기
    List<MessageDTO> getMessagesByRoomId(String roomId);

    MessageDTO getLastMessageByRoomId(String roomId);
}
