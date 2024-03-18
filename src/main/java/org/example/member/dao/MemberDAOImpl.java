package org.example.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.member.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

// 회원 데이터 액세스 객체의 구현체
@Repository
public class MemberDAOImpl implements MemberDAO{
    @Autowired
    private SqlSession sqlSession;

    // 로그인
    @Override
    public MemberDTO checkLogin(String userId, String userPw) {
        // DB에서 입력받은 아이디와 비밀번호 (userId, userPw) 값이 DB에 저장되어있는 아이디와 비밀번호와 같다면 행 전체(객체) 리턴
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("userPw", userPw);
        return sqlSession.selectOne("mapper.member.checkLogin", params);
    }

    @Override
    public MemberDTO findMemberById(String userId) {
        return sqlSession.selectOne("mapper.member.findMemberById", userId);
    }


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

    public String findById(String userName, String userBirth){
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("userBirth", userBirth);
        return  sqlSession.selectOne("mapper.member.findById", params);
    }

    public int findByPw(String userId, String userBirth){
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("userBirth", userBirth);
        return  sqlSession.selectOne("mapper.member.findByPw", params);
    }

}
