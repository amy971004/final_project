package org.example.member.service;

import org.example.member.dao.MemberDAO;
import org.example.member.dto.MemberDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDAO dao;

    //로그인
    @Override
    public MemberDTO login(String userId, String userPw) {
        MemberDTO member = dao.findMemberById(userId); // 사용자 ID로 저장된 사용자 정보 조회
        if (member != null && BCrypt.checkpw(userPw, member.getUserPw())) {
            return member; // 로그인 성공 시 사용자 정보 반환
        }
        return null; // 로그인 실패 시 null 반환
    }


    // 회원 추가
    @Override
    public int addMember(MemberDTO member) {
        String hashedPw = BCrypt.hashpw(member.getUserPw(), BCrypt.gensalt());
        member.setUserPw(hashedPw); // 해시된 비밀번호로 설정
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

    public String findById(String userName, String userBirth){
        return dao.findById(userName, userBirth);
    }

    public boolean findByPw(String userId, String userBirth){
        return dao.findByPw(userId, userBirth) == 1;
    }

}
