package org.example.profile.dao;

import org.example.post.dto.*;
import org.example.profile.dto.ProfileDTO;

import java.util.List;
import java.util.Map;

public interface ProfileDAO {
    ProfileDTO profileView(String accountID);

    // 아이디 중복 검사를 위한 메서드 추가
    int checkId(String userId);

    int checkNickname(String nickname);

    int updateProfile(ProfileDTO dto);

    int upload(ProfileDTO dto);

    List<PostDTO> postView(String userNickname);

    List<ImageDTO> imageView();

    List<CommentDTO> commentView();

    List<LikeDTO> likeView();

    String getuserNickname(String accountId);

    List<LikeDTO> likes(String postId);
}
