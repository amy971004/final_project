package org.example.member.service;

import org.example.member.dao.MemberDAO;
import org.example.member.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDAO dao;

    // 회원 추가
    @Override
    public int addMember(MemberDTO member) {
        return dao.addMember(member);
    }

    // 아이디 중복 확인
    @Override
    public boolean checkId(String userId) {
        return dao.checkId(userId) == 0; // 아이디가 존재하지 않으면 true 반환
    }

    // 비밀번호 중복 확인
    @Override
    public boolean checkNickname(String userNickname) {
        return dao.checkNickname(userNickname) == 0; // 아이디가 존재하지 않으면 true 반환
    }

}
