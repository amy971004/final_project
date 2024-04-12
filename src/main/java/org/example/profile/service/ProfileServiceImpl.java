package org.example.profile.service;

import org.example.post.dto.*;
import org.example.profile.dao.ProfileDAO;
import org.example.profile.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProfileServiceImpl implements ProfileService {
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

    @Override
    public List<PostDTO> postView(String userNickname) {
        return dao.postView(userNickname);
    }

    @Override
    public List<ImageDTO> imageView() {
        return dao.imageView();
    }

    @Override
    public List<CommentDTO> commentView() {
        return dao.commentView();
    }

    @Override
    public List<LikeDTO> likeView() {
        return dao.likeView();
    }

    @Override
    public List<LikeDTO> likes(String postId) {
        return dao.likes(postId);
    }

    @Override
    public List<FollowDTO> followView(String nickname) {
        return dao.followView(nickname);
    }

    @Override
    public List<FollowDTO> followingView(String userNickname) {
        return dao.followingView(userNickname);
    }

    @Override
    public String findAccountId(String userNickname) {
        return dao.findAccountId(userNickname);
    }

    @Override
    public int deletePost(int postId) {
        return dao.deletePost(postId);
    }

}