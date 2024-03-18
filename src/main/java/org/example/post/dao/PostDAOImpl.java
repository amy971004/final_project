package org.example.post.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PostDAOImpl implements PostDAO{
    @Autowired
    private SqlSession sqlSession;


    @Override
    public void addFile(Map<String, Object> postInfo) {
        sqlSession.insert("mapper.post.addFile", postInfo);
    }
}
