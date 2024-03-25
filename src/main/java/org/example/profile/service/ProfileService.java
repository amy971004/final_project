package org.example.profile.service;

import org.example.profile.dto.ProfileDTO;

public interface ProfileService {
    ProfileDTO profileView(String accountID);

    // 아이디 중복 검사
    boolean checkId(String userId, String id);

    boolean checkNickname(String nickname, String userNickname);


    int updateProfile(ProfileDTO member);

    int upload(ProfileDTO dto);
}
