package org.example.message.dao;

import org.example.message.dto.RoomDTO;

import java.util.List;

public interface RoomDAO {
    // 전달받은 receiverId로 receiverAccountId 찾기
    String findReceiverAccountId(String receiverId);

    // 수신자, 발신자의 accountId로 두사람만의 채팅방이 존재하는지 확인
    RoomDTO findRoomByUserIds(String roomMetaName);

    // DB에 채팅방 정보 저장
    void createRoom(RoomDTO newRoom);

    // 발신자 accountId로 내가 참여하고있는 모든 채팅방 불러오기
    List<RoomDTO> findAllRoomsByAccountId(String senderAccountId);

    // 채팅 창여자 목록 테이플에 추가
    void addParticipant(String roomId, String accountId);
}
