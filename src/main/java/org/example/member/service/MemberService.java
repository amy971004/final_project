package org.example.member.service;

import org.example.member.dto.MemberDTO;

public interface MemberService {

    // 로그인
    MemberDTO login(String userId, String userPw);
    // 회원가입
    int addMember(MemberDTO member);

    // 아이디 중복 검사
    boolean checkId(String userId);

    // 닉네임 중복 검사
    boolean checkNickname(String userNickname);

}
