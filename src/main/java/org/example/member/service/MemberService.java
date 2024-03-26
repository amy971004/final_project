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

    // 아이디 찾기
    String findById(String userName, String userBirth);

    // 비밀번호 찾기
    boolean findByPw(String userId, String userBirth);

    // 비밀번호 변경 - 입력받은 아이디로 식별자 아이디 찾기
    String findByAccountID_useId(String userId);

    // 비밀번호 변경
    boolean changePw(String changePw, String accountId);

    // 이메일 인증
    int updateMailKey(MemberDTO memberDTO) throws Exception;
    int updateMailAuth(MemberDTO memberDTO) throws Exception;
    int updateMailFail(String userId) throws Exception;

    String sendMail(String userEmail) throws Exception;
}
