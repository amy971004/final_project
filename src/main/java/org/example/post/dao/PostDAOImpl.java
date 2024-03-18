package org.example.post.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.post.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PostDAOImpl implements PostDAO{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public int selectContentNo() {
        return sqlSession.selectOne("mapper.post.selectContentNo");
    }

    @Override
    public int addPost(Map<String, Object> postMap) {
        return sqlSession.insert("mapper.post.addPost", postMap);
    }

    @Override
    public int selectImageNo() {
        return sqlSession.selectOne("mapper.post.selectImageNo");
    }

    @Override
    public void addImage(List<ImageDTO> imageFileList) {
        sqlSession.insert("mapper.post.imageFileList");
    }
}
