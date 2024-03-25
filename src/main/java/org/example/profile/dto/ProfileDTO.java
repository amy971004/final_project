package org.example.profile.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ProfileDTO {
    private String accountId;
    private String userId;
    private String password;
    private String userNickname;
    private String userName;
    private String email;
    private int birthday;
    private String phoneNumber;
    private String profileImg;
    private String introduction;
    private Date joinDate;
    private String role;
}
