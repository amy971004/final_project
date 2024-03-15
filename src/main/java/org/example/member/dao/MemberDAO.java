package org.example.member.dao;

import org.example.member.dto.MemberDTO;

public interface MemberDAO {
    int addMember(MemberDTO member);

    // 아이디 중복 검사를 위한 메서드 추가
    int checkId(String userId);

    // 닉네임 중복 검사를 위한 메서드 추가
    int checkNickname(String userNickname);

}
