package org.example.member.service;

import org.example.member.dao.MemberDAO;
import org.example.member.dto.MemberDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberDAO dao;

    @Autowired
    public MemberServiceImpl(MemberDAO dao) {
        this.dao = dao;
    }

    // 로그인 part1
    // 아이디 비밀번호를 입력받아 로그인
    @Override
    public MemberDTO login(String userId, String userPw) {
        // 사용자 ID로 저장된 사용자 정보 조회
        MemberDTO member = dao.findMemberById(userId);
        if (member != null && BCrypt.checkpw(userPw, member.getUserPw())) {
            return member; // 로그인 성공 시 사용자 정보 반환
        }
        return null; // 로그인 실패 시 null 반환
    }

    // 회원가입
    // 가입정보를 입력받은 후 회원가입
    @Override
    public int addMember(MemberDTO member) {
        String hashedPw = BCrypt.hashpw(member.getUserPw(), BCrypt.gensalt());
        member.setUserPw(hashedPw); // 해시된 비밀번호로 설정
        return dao.addMember(member);
    }

    // 회원가입 유효성 검증 (아이디 중복 확인) - javascript - JQuery AJAX
    // 회원 ID중 입력받은 ID와 일치하는 정보가 있으면 (이미 사용중이면) false, 없으면 (사용 가능하면) true
    @Override
    public boolean checkId(String userId) {
        return dao.checkId(userId) == 0; // 아이디가 존재하지 않으면 true 반환
    }

    // 닉네임 유효성 검증 (닉네임 중복 확인) - javascript - JQuery AJAX
    // 회원 닉네임중 입력받은 ID와 일치하는 정보가 있으면 (이미 사용중이면) false, 없으면 (사용 가능하면) true
    @Override
    public boolean checkNickname(String userNickname) {
        return dao.checkNickname(userNickname) == 0; // 아이디가 존재하지 않으면 true 반환
    }

    // 아이디 찾기
    // 이름과 생년월일을 입력받고 아이디 찾기
    @Override
    public String findById(String userName, String userBirth){
        return dao.findById(userName, userBirth);
    }

    // 비밀번호 변경 part1
    // 비밀번호 찾기 입력창에서 입력한 아이디로 식별자 아이디 찾기
    @Override
    public boolean findByPw(String userId, String userBirth){
        return dao.findByPw(userId, userBirth) == 1;
    }

    // 비밀번호 변경 part2
    // 식별자 아이디로 회원정보를 찾은 후 해당 회원의 비밀번호를 입력받은 비밀번호로 변경
    @Override
    public String findByAccountID_useId(String userId) {
        return dao.findByAccountID_useId(userId);
    }


    // 비밀번호 변경 part3
    // 식별자 아이디로 회원정보를 찾은 후 입력받은 비밀번호로 변경
    @Override
    public boolean changePw(String inputPw, String accountId) {
        String changePw = BCrypt.hashpw(inputPw, BCrypt.gensalt());
        return dao.changePw(changePw, accountId);
    }

}
