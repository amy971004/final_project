package org.example.profile.dao;

import org.example.profile.dto.ProfileDTO;

import java.util.List;

public interface ProfileDAO {
    ProfileDTO profileView(String accountID);

    // 아이디 중복 검사를 위한 메서드 추가
    int checkId(String userId);

    int checkNickname(String nickname);

    int updateProfile(ProfileDTO dto);

    int upload(ProfileDTO dto);
}
