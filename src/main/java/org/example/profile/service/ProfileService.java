package org.example.profile.service;

import org.example.post.dto.*;
import org.example.profile.dto.ProfileDTO;

import java.util.List;
import java.util.Map;

public interface ProfileService {
    ProfileDTO profileView(String accountID);

    // 아이디 중복 검사
    boolean checkId(String userId, String id);

    boolean checkNickname(String nickname, String userNickname);


    int updateProfile(ProfileDTO member);

    int upload(ProfileDTO dto);

    List<PostDTO> postView(String userNickname);

    List<ImageDTO> imageView();

    List<CommentDTO> commentView();

    List<LikeDTO> likeView();

    List<LikeDTO> likes(String postId);

    List<FollowDTO> followView(String nickname);

    List<FollowDTO> followingView(String userNickname);

    String findAccountId(String userNickname);

    int deletePost(int postId);
}
