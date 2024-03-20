package org.example.member.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private String accountID;
    private String userId;
    private String userPw;
    private String userNickname;
    private String userName;
    private String userEmail;
    private int userBirthday;
    private String userPhoneNumber;
    private String profileImg;
    private String introduction;
    private String ROLE;
}
