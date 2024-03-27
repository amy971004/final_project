package org.example.message.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAOImpl implements MessageDAO {

    private final SqlSession sqlSession;

    @Autowired
    public MessageDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

}
