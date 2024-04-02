package org.example.post.service;

import org.example.post.dto.CommentDTO;
import org.example.post.dto.ImageDTO;
import org.example.post.dto.PostDTO;

import java.util.List;
import java.util.Map;

public interface PostService {

    // 게시물(이미지) 대한 정보 저장
    void addPost(Map<String, Object> postInfo);
    // 닉네임 가져오기
    String selectNickname(String accountID);
    // 게시물 번호 가져오기
    int selectPostId();

    // 설지연 ------------------------------------------------------
    List<PostDTO> postList();

    int likeCheck(String loginId, int contentNo);

    int bookmarkCheck(String loginId, int contentNo);

    List<String> getTag(int contentNo);

    List<CommentDTO> getCommentList();

    int likeCnt(int contentNo);

    int deletLike(int postId, String loginNickname);

    int pushLike(int postId, String loginNickname);

    String loginNickname(String accountId);

    int deletBook(int postId, String loginNickname);

    int pushBook(int postId, String loginNickname);

    // 댓글 저장
    void inputComment(Map<String,Object> comment);

    // 이미지 전체정보 가져오기
    List<ImageDTO> getImageList();

    // 게시물 삭제
    void deletePost(int postId);

    // 유저 프로필 이미지 가져오기
    String getProfileImg(String userNickname);

    String findUserId(String userNickname);
}
