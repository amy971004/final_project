package org.example.uploadpost.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.uploadpost.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PostDAOImpl implements PostDAO{
    @Autowired
    private SqlSession sqlSession;

    // 게시물 정보 저장
    @Override
    public void addPost(Map<String, Object> postInfo) {
        sqlSession.insert("mapper.post.addPost", postInfo);
    }

    // 게시물 정보 저장
    @Override
    public void addImage(List<ImageDTO> imageFileInfo) {
        sqlSession.insert("mapper.post.addImage", imageFileInfo);
    }

    // 게시물 번호 가져오기
    @Override
    public int selectPostId() {
        return sqlSession.selectOne("mapper.post.selectPostId");
    }

    @Override
    public int selectImageNo() {
        return sqlSession.selectOne("mapper.post.selectImageNo");
    }

}
