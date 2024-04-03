package org.example.message.service;

import org.example.message.dto.RoomDTO;

import java.util.List;

public interface RoomService {

    // 전달받은 receiverId로 receiverAccountId 찾기
    String findReceiverAccountId(String receiverId);

    // 수신자, 발신자의 accountId로 채팅방이 이미 있는 지 확인하고 있으면 해당 채팅방 반환, 없으면 새로 생성
    RoomDTO findOrCreateChatRoom(String senderAccountId, String receiverAccountId, String senderName,String receiverName);

    // 발신자 accountId로 내가 참여하고있는 모든 채팅방 불러오기
    List<RoomDTO> findAllRoomsByAccountId(String userId);
}
