package org.example.message.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.member.dto.MemberDTO;
import org.example.message.dto.MessageDTO;
import org.example.message.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoomDAOImpl implements RoomDAO {

    private final SqlSession sqlSession;

    @Autowired
    public RoomDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // 전달받은 receiverId로 receiverAccountId 찾기
    @Override
    public String findReceiverAccountId(String receiverId) {
        return sqlSession.selectOne("mapper.room.findReceiverAccountId", receiverId);
    }

    // 수신자, 발신자의 accountId로 두사람만의 채팅방이 존재하는지 확인
    @Override
    public RoomDTO findRoomByUserIds(String roomMetaName) {
        return sqlSession.selectOne("mapper.room.findRoomByUserIds", roomMetaName);
    }

    // DB에 채팅방 정보 저장
    @Override
    public void createRoom(RoomDTO newRoom) {
        sqlSession.insert("mapper.room.createRoom", newRoom);
    }

    // 채팅방 참여자 추가
    @Override
    public void addParticipant(String roomId, String accountId, String userId, String userName) {
        // 2개 이상의 파라미터를 넘기기 위해 해시맵 사용
        System.out.println("#################ROOMID : "+ roomId + "###################################");
        Map<String, Object> params = new HashMap<>();
        params.put("roomId", roomId);
        params.put("accountId", accountId);
        params.put("userId", userId);
        params.put("userName", userName);
        sqlSession.insert("mapper.room.addParticipant", params);
    }

    // RoomId로 Room 정보 반환
    @Override
    public RoomDTO getRoomByRoomId(String roomId) {
        return sqlSession.selectOne("mapper.room.getRoomByRoomId", roomId);
    }

    // 발신자 accountId로 내가 참여하고있는 모든 채팅방 불러오기
    @Override
    public List<RoomDTO> findAllRoomsByAccountId(String senderAccountId) {
        return sqlSession.selectList("mapper.room.findAllRoomsByAccountId", senderAccountId);
    }

    // 발신자 accountId로 내가 참여하고 있는 모든 채팅방과 그 마지막 메시지 정보 불러오기
    @Override
    public List<RoomDTO> findAllRoomsWithLastMessage(String senderAccountId) {
        List<RoomDTO> rooms = findAllRoomsByAccountId(senderAccountId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 날짜 형식 지정

        for (RoomDTO room : rooms) {
            MessageDTO lastMessage = sqlSession.selectOne("mapper.message.getLastMessageByRoomId", room.getRoomId());
            if (lastMessage != null) {
                room.setLastMessage(lastMessage.getMessageText());
                // 날짜 형식 변환하여 새로운 필드에 저장
                room.setFormattedLastMessageDate(formatter.format(lastMessage.getSentAt()));
            }
        }
        return rooms;
    }

    // RoomDAOImpl에 메서드 구현
    @Override
    public String findOpponentName(String roomId, String myAccountId) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomId", roomId);
        params.put("myAccountId", myAccountId);
        return sqlSession.selectOne("mapper.room.findOpponentName", params);
    }

    @Override
    @Transactional
    public boolean deleteRoom(String roomId) {

        sqlSession.delete("mapper.room.deleteMessages", roomId);

        int result1 = sqlSession.delete("mapper.room.deleteParticipants", roomId);

        if (0 < result1){
            int result2 = sqlSession.delete("mapper.room.deleteRoom", roomId);
            return 0 < result2;
        } else {
            return false;
        }

    }

    @Override
    public int findParticipantsByRoomId(String roomId) {
        return sqlSession.selectOne("mapper.room.findParticipantsByRoomId", roomId);
    }

    @Override
    public List<String> findReceiver(String senderAccountId) {
        return sqlSession.selectList("mapper.room.findReceiver", senderAccountId);
    }

    @Override
    public MemberDTO selectReceiver(String accountId) {
        return sqlSession.selectOne("mapper.room.selectReceiver", accountId);
    }


}
