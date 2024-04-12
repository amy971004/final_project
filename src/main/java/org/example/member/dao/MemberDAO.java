package org.example.member.dao;

import org.example.member.dto.MemberDTO;

import java.util.List;

public interface MemberDAO {

    // 로그인 part1
    // 아이디 비밀번호를 입력받아 로그인
    MemberDTO checkLogin(String userId, String userPw);

    // 로그인 part2
    // 입력받은 아이디와 일치하는 회원정보 가져오기
    MemberDTO findMemberById(String userId);

    // 회원가입
    // 가입정보를 입력받은 후 회원가입
    int addMember(MemberDTO member);

    // 회원가입 유효성 검증 (아이디 중복 확인) - javascript - JQuery AJAX
    // 회원 ID중 입력받은 ID와 일치하는 정보가 있으면 (이미 사용중이면) false, 없으면 (사용 가능하면) true
    int checkId(String userId);

    // 닉네임 유효성 검증 (닉네임 중복 확인) - javascript - JQuery AJAX
    // 회원 닉네임중 입력받은 ID와 일치하는 정보가 있으면 (이미 사용중이면) false, 없으면 (사용 가능하면) true
    int checkNickname(String userNickname);

    // 아이디 찾기
    // 이름과 생년월일을 입력받고 아이디 찾기
    String findById(String userName, String userBirth);

    // 비밀번호 변경 part1
    // 비밀번호 찾기 입력창에서 입력한 아이디로 식별자 아이디 찾기
    int findByPw(String userId, String userBirth);

    // 비밀번호 변경 part2
    // 식별자 아이디로 회원정보를 찾은 후 해당 회원의 비밀번호를 입력받은 비밀번호로 변경
    String findByAccountID_useId(String userId);

    // 비밀번호 변경 part3
    // 식별자 아이디로 회원정보를 찾은 후 입력받은 비밀번호로 변경
    boolean changePw(String hashPw, String accountId);

    int updateMailKey(MemberDTO memberDTO) throws Exception;
    int updateMailAuth(MemberDTO memberDTO) throws Exception;
    int emailAuthFail(String userId) throws Exception;

    // 전달받은 accountId로 일치하는 회원정보 가져오기
    MemberDTO findMemberByAccountId(String accountId);

    List<MemberDTO> searchUser(String userName);

}
