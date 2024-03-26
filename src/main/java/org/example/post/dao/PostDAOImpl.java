package org.example.post.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.member.dto.MemberDTO;
import org.example.post.dto.ImageDTO;
import org.example.post.dto.CommentDTO;
import org.example.post.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostDAOImpl implements PostDAO {
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

    @Override
    public String selectNickname(String accountID) {
        return sqlSession.selectOne("mapper.post.selectNickname", accountID);
    }


    // 설지연 -------------------------------------------------------------


    @Override
    public List<PostDTO > postList() {
        return sqlSession.selectList("mapper.post.postList");
    }

    // 좋아요 여부
    @Override
    public int likeCheck(String loginId, int contentNo) {
        Map<String,Object> data = new HashMap<>();
        data.put("loginId",loginId);
        data.put("contentNo",contentNo);
        return sqlSession.selectOne("mapper.post.likeCheck",data);
    }

    @Override
    public int bookmarkCheck(String loginId, int contentNo) {
        Map<String,Object> data = new HashMap<>();
        data.put("loginId",loginId);
        data.put("contentNo",contentNo);
        return sqlSession.selectOne("mapper.post.bookmarkCheck",data);
    }

    @Override
    public List<String> getTag(int contentNo) {
        return sqlSession.selectList("mapper.post.getTag",contentNo);
    }

    @Override
    public List<CommentDTO> getCommentList() {
        return sqlSession.selectList("mapper.post.getCommentList");
    }

    @Override
    public int likeCnt(int contentNo) {
        return sqlSession.selectOne("mapper.post.likeCnt",contentNo);
    }

    @Override
    public int deletLike(int postId, String loginNickname) {
        Map<String,Object> data = new HashMap<>();
        data.put("postId",postId);
        data.put("loginNickname",loginNickname);
        return sqlSession.delete("mapper.post.deletLike",data);
    }

    @Override
    public int pushLike(int postId, String loginNickname) {
        Map<String,Object> data = new HashMap<>();
        int newLikeNo = selectNewLikeId();
        data.put("newLikeId",newLikeNo+1);
        data.put("postId",postId);
        data.put("loginNickname",loginNickname);
        return sqlSession.insert("mapper.post.pushLike",data);
    }

    @Override
    public int selectNewLikeId() {
        return sqlSession.selectOne("mapper.post.selectNewLikeId");
    }

}
