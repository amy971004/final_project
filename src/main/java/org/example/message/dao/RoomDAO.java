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
    void addParticipant(String roomId, String accountId, String userId, String userName);

    // roomId로 RoomDTO 반환
    RoomDTO getRoomByRoomId(String roomId);

    // 발신자 accountId로 내가 참여하고 있는 모든 채팅방과 그 마지막 메시지 정보 불러오기
    List<RoomDTO> findAllRoomsWithLastMessage(String senderAccountId);

    // RoomDAOImpl에 메서드 구현
    String findOpponentName(String roomId, String myAccountId);

    // roomId 로 해당 Room 삭제하기
    boolean deleteRoom(String roomId);

    int findParticipantsByRoomId(String roomId);
}
