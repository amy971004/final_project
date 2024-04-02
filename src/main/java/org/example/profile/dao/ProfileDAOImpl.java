package org.example.profile.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.member.dto.MemberDTO;
import org.example.post.dto.*;
import org.example.profile.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProfileDAOImpl implements ProfileDAO{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public ProfileDTO profileView(String accountID) {
        return sqlSession.selectOne("mapper.profile.profileView", accountID);
    }

    // 아이디 중복 확인
    @Override
    public int checkId(String userId) {
        return sqlSession.selectOne("mapper.profile.checkId", userId);
    }

    @Override
    public int checkNickname(String nickname) {
        return sqlSession.selectOne("mapper.profile.checkNickname", nickname);
    }

    @Override
    public int updateProfile(ProfileDTO dto) {
        return sqlSession.update("mapper.profile.updateProfile", dto);
    }

    @Override
    public int upload(ProfileDTO dto) {
        return sqlSession.update("mapper.profile.upload", dto);
    }

    @Override
    public List<PostDTO> postView(String userNickname) {
        return sqlSession.selectList("mapper.profile.postView", userNickname);
    }

    @Override
    public List<ImageDTO> imageView() {
        return sqlSession.selectList("mapper.profile.imageView");
    }

    @Override
    public List<CommentDTO> commentView() {
        return sqlSession.selectList("mapper.profile.commentView");
    }

    @Override
    public List<LikeDTO> likeView() {
        return sqlSession.selectList("mapper.profile.likeView");
    }

    @Override
    public List<LikeDTO> likes(String postId) {
        return sqlSession.selectList("mapper.profile.likes", postId);
    }

}
