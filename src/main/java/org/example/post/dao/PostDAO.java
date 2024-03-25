package org.example.post.dao;

import org.example.post.dto.ImageDTO;
import org.example.post.dto.CommentDTO;
import org.example.post.dto.PostDTO;

import java.util.List;
import java.util.Map;

public interface PostDAO {

    // 게시물 정보 저장
    void addPost(Map<String, Object> postInfo);

    // 이미지 정보 저장
    void addImage(List<ImageDTO> imageFileInfo);

    // 게시물 번호 가져오기
    int selectPostId();

    // 이미지 번호 가져오기
    int selectImageNo();

    // 회원 닉네임 가져오기
    String selectNickname(String accountID);

    // 설지연 -----------------------------------------
    List<PostDTO> postList();

    int likeCheck(String loginId, int contentNo);

    int bookmarkCheck(String loginId, int contentNo);

    List<String> getTag(int contentNo);

    List<CommentDTO> getCommentList();

    int likeCnt(int contentNo);

    int deletLike(int postId, String loginNickname);

    int pushLike(int postId, String loginNickname);

    int selectNewLikeId();


}
