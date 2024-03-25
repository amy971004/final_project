package org.example.admin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOImpl implements AdminDAO {

    private final SqlSession sqlSession;

    @Autowired
    public AdminDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public int deleteUser(String userId) {
        return sqlSession.delete("mapper.member.deleteUser", userId);
    }

}
