package org.example.profile.service;

import org.example.profile.dao.ProfileDAO;
import org.example.profile.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private ProfileDAO dao;

    @Override
    public ProfileDTO profileView(String accountID) {
        return dao.profileView(accountID);
    }

    // 아이디 중복 확인
    @Override
    public boolean checkId(String userId, String id) {
        return (dao.checkId(userId) == 0) || (userId.equals(id)); // 아이디가 존재하지 않거나 기존 아이디과 같으면 true 반환
    }

    // 닉네임 중복 확인
    @Override
    public boolean checkNickname(String nickname, String userNickname) {
        return (dao.checkNickname(nickname) == 0) || (userNickname.equals(nickname));
    }

    @Override
    public int updateProfile(ProfileDTO dto) {
        return dao.updateProfile(dto);
    }

    @Override
    public int upload(ProfileDTO dto) {
        return dao.upload(dto);
    }
}
