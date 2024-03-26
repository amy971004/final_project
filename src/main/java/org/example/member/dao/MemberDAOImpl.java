package org.example.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.member.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.Namespace;
import java.util.HashMap;
import java.util.Map;

// 회원 데이터 액세스 객체의 구현체
@Repository
public class MemberDAOImpl implements MemberDAO{

    private final SqlSession sqlSession;

    @Autowired
    public MemberDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // 로그인 part1
    // 아이디 비밀번호를 입력받아 로그인
    @Override
    public MemberDTO checkLogin(String userId, String userPw) {
        // 2개 이상의 파라미터를 넘기기 위해 해시맵 사용
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("userPw", userPw);
        // DB에서 입력받은 아이디와 비밀번호 (userId, userPw) 값이 DB에 저장되어있는 아이디와 비밀번호와 같다면 행 전체(객체) 리턴
        return sqlSession.selectOne("mapper.member.checkLogin", params);
    }

    // 로그인 part2
    // 입력받은 아이디와 일치하는 회원정보 가져오기
    @Override
    public MemberDTO findMemberById(String userId) {
        return sqlSession.selectOne("mapper.member.findMemberById", userId);
    }

    // 회원가입
    // 가입정보를 입력받은 후 회원가입
    @Override
    public int addMember(MemberDTO member) {
        return sqlSession.insert("mapper.member.addMember", member);
    }

    // 회원가입 유효성 검증 (아이디 중복 확인) - javascript - JQuery AJAX
    // 회원 ID중 입력받은 ID와 일치하는 정보가 있으면 (이미 사용중이면) false, 없으면 (사용 가능하면) true
    @Override
    public int checkId(String userId) {
        return sqlSession.selectOne("mapper.member.checkId", userId);
    }

    // 닉네임 유효성 검증 (닉네임 중복 확인) - javascript - JQuery AJAX
    // 회원 닉네임중 입력받은 ID와 일치하는 정보가 있으면 (이미 사용중이면) false, 없으면 (사용 가능하면) true
    @Override
    public int checkNickname(String userNickname) {
        return sqlSession.selectOne("mapper.member.checkNickname", userNickname);
    }

    // 아이디 찾기
    // 이름과 생년월일을 입력받고 아이디 찾기
    @Override
    public String findById(String userName, String userBirth){
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("userBirth", userBirth);
        return  sqlSession.selectOne("mapper.member.findById", params);
    }

    // 비밀번호 찾기 part1
    // 입력받은 아이디와 생년월일과 일치하는 회원정보 찾기
    @Override
    public int findByPw(String userId, String userBirth){
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("userBirth", userBirth);
        return  sqlSession.selectOne("mapper.member.findByPw", params);
    }

    // 비밀번호 변경 part2
    // 식별자 아이디로 회원정보를 찾은 후 해당 회원의 비밀번호를 입력받은 비밀번호로 변경
    @Override
    public String findByAccountID_useId(String userId) {
        return sqlSession.selectOne("mapper.member.findByAccountID_useId", userId);
    }

    // 비밀번호 변경 part3
    // 식별자 아이디로 회원정보를 찾은 후 입력받은 비밀번호로 변경
    @Override
    public boolean changePw(String changePw, String accountId) {
        Map<String, Object> params = new HashMap<>();
        params.put("changePw", changePw);
        params.put("accountId", accountId);
        return sqlSession.update("mapper.member.changePw", params) > 0;
    }

    @Override
    public int updateMailKey(MemberDTO memberDTO) throws Exception {
        return sqlSession.update("mapper.member.updateMailKey", memberDTO);
    }

    @Override
    public int updateMailAuth(MemberDTO memberDTO) throws Exception {
        return sqlSession.update("mapper.member.updateMailAuth", memberDTO);
    }

    @Override
    public int emailAuthFail(String userId) throws Exception {
        return sqlSession.selectOne("mapper.member.updateAuthFail", userId);
    }

}
