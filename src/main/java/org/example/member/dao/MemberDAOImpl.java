package org.example.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.member.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

// 회원 데이터 액세스 객체의 구현체
@Repository
public class MemberDAOImpl implements MemberDAO{
    @Autowired
    private SqlSession sqlSession;

    // 회원 추가
    @Override
    public int addMember(MemberDTO member) {
        return sqlSession.insert("mapper.member.addMember", member);
    }

    // 아이디 중복 확인
    @Override
    public int checkId(String userId) {
        return sqlSession.selectOne("mapper.member.checkId", userId);
    }

    // 닉네임 중복 확인
    @Override
    public int checkNickname(String userNickname) {
        return sqlSession.selectOne("mapper.member.checkNickname", userNickname);
    }

}
