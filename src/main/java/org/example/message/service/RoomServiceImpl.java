package org.example.message.service;

import org.example.member.dao.MemberDAO;
import org.example.member.dto.MemberDTO;
import org.example.message.dao.RoomDAO;
import org.example.message.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomDAO dao;
    private final MemberDAO memberDAO;

    @Autowired
    public RoomServiceImpl(RoomDAO dao, MemberDAO memberDAO) {
        this.dao = dao;
        this.memberDAO = memberDAO;
    }

    // 전달받은 receiverId로 receiverAccountId 찾기
    @Override
    public String findReceiverAccountId(String receiverId) {
        return dao.findReceiverAccountId(receiverId);
    }

    // 수신자, 발신자의 accountId로 채팅방이 이미 있는 지 확인하고 있으면 해당 채팅방 반환, 없으면 새로 생성
    @Override
    @Transactional
    public RoomDTO findOrCreateChatRoom(String senderAccountId, String receiverAccountId, String senderName,String receiverName) {

        // 두사람만의 채팅방 찾기
        // UUID 를 알파벳 순으로 정렬
        String[] ids = {senderAccountId, receiverAccountId};
        Arrays.sort(ids);
        // 정렬된 UUID 를 결합하여 메타 이름 생성
        String roomMetaName = ids[0] + "_" + ids[1];

        // 첫번째 단계: 두 사용자 사이의 채팅방이 이미 존재하는지 확인합니다.
        RoomDTO existingRoom = dao.findRoomByUserIds(roomMetaName);
        if (existingRoom != null) {
            // 존재한다면, 기존의 채팅방 정보를 반환합니다.
            return existingRoom;
        }

        // 채팅방 생성 로직 (예: 채팅방 이름 설정, 참여자 설정 등)
        // 이 예제에서는 간단하게 senderId와 receiverId만 사용하지만,
        // 실제 애플리케이션에서는 보다 복잡한 로직이 필요할 수 있습니다.

        // 두번째 단계: 존재하지 않는다면, 새로운 채팅방을 생성합니다.
        RoomDTO newRoom = new RoomDTO();

        // roomName, roomMetaName 설정
        newRoom.setRoomName("'" + senderName + "'님과 '" + receiverName + "'님의 채팅방");
        newRoom.setRoomMetaName(roomMetaName);
        newRoom.setRoomReceiverName(receiverName);

        // DB에 채팅방 정보 저장
        dao.createRoom(newRoom);

        // 새로 생성된 채팅방 정보를 반환합니다.
        return newRoom;
    }

    // 발신자 accountId로 내가 참여하고있는 모든 채팅방 불러오기
    @Override
    public List<RoomDTO> findAllRoomsByAccountId(String senderAccountId) {
        return dao.findAllRoomsWithLastMessage(senderAccountId);
    }

}
