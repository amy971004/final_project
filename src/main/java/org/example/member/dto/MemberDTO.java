package org.example.member.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private String userId;
    private String userPw;
    private String userNickname;
    private String userName;
    private String userEmail;
    private int userBirthday;
    private String userPhoneNumber;
}
