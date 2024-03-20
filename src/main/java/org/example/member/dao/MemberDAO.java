package org.example.member.dao;

import org.example.member.dto.MemberDTO;

public interface MemberDAO {

    // 로그인
    MemberDTO checkLogin(String userId, String userPw);

    // 로그인
    MemberDTO findMemberById(String userId);
    
    // 회원가입
    int addMember(MemberDTO member);

    // 아이디 중복 검사를 위한 메서드 추가
    int checkId(String userId);

    // 닉네임 중복 검사를 위한 메서드 추가
    int checkNickname(String userNickname);

    // 아이디 찾기
    String findById(String userName, String userBirth);

    // 비밀번호 찾기
    int findByPw(String userId, String userBirth);

    // 비밀번호 변경
    boolean changePw(String hashPw, String accountId);

    // 비밀번호 찾기 - 입력받은 아이디로 식별자 아이디 찾기
    String findByAccountID_useId(String userId);
}
