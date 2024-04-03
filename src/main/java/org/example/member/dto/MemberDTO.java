package org.example.member.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MemberDTO {
    private String accountID; // 식별자 아이디
    private String userId; // 회원 아이디
    private String userPw; // 회원 비밀번호
    private String userNickname; // 회원 닉네임
    private String userName; // 회원 이름
    private String userEmail; //  회원 이메일
    private int userBirthday; // 회원 생년월일
    private String userPhoneNumber; // 회원 핸드폰 번호
    private Date userJoinDate; // 회원가입 일자
    private String profileImg; // 회원 프로필사진 저장경로
    private String introduction; // 회원 프로필 소개글
    private String ROLE; // 회원 권한
}
