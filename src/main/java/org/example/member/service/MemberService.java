package org.example.member.service;

import org.example.member.dto.MemberDTO;

public interface MemberService {
    int addMember(MemberDTO member);

    boolean checkId(String userId);

    boolean checkNickname(String userNickname);

}
