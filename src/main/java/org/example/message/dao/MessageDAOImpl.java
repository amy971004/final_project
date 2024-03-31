package org.example.message.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.message.dto.MessageDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class MessageDAOImpl implements MessageDAO {
    private final SqlSession sqlSession;

    @Autowired
    public MessageDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // 메세지 저장
    @Override
    public void saveMessage(MessageDTO messageDTO) {
        sqlSession.insert("mapper.message.saveMessage", messageDTO);
    }

    // roomId로 해당 채팅방의 모든 참가자 accountId 배열로 반환
    @Override
    public List<String> getParticipantsByRoomId(String roomId){
        return sqlSession.selectList("mapper.message.getParticipantsByRoomId", roomId);
    }

    // roomId 로 모든 message 가져오기
    @Override
    public List<MessageDTO> getMessagesByRoomId(String roomId) {
        return sqlSession.selectList("mapper.message.getMessagesByRoomId", roomId);
    }

    @Override
    public MessageDTO getLastMessageByRoomId(String roomId) {
        return sqlSession.selectOne("mapper.message.getLastMessageByRoomId", roomId);
    }

}
