package org.example.message.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.message.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void addParticipant(String roomId, String accountId) {
        // 2개 이상의 파라미터를 넘기기 위해 해시맵 사용
        Map<String, Object> params = new HashMap<>();
        params.put("roomId", roomId);
        params.put("accountId", accountId);
        sqlSession.insert("mapper.room.addParticipant", params);
    }

    // 발신자 accountId로 내가 참여하고있는 모든 채팅방 불러오기
    @Override
    public List<RoomDTO> findAllRoomsByAccountId(String senderAccountId) {
        return sqlSession.selectList("mapper.room.findAllRoomsByAccountId", senderAccountId);
    }

}
