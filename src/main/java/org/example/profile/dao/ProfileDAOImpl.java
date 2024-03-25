package org.example.profile.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.member.dto.MemberDTO;
import org.example.profile.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
