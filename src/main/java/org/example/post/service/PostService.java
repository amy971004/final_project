package org.example.post.service;

import org.example.post.dto.CommentDTO;
import org.example.post.dto.ImageDTO;
import org.example.post.dto.PostDTO;
import org.example.profile.dto.ProfileDTO;

import java.util.List;
import java.util.Map;

public interface PostService {

    // 게시물(이미지) 대한 정보 저장
    void addPost(Map<String, Object> postInfo);
    // 닉네임 가져오기
    String selectNickname(String accountID);
    // 게시물 번호 가져오기
    int selectPostId();
    // 프로필 가져오기
    ProfileDTO selectProfile(String accountId);
    // 해당 게시물 정보들 가져오기
    Map<String, Object> postDetail(int postId);
    // 게시물 수정
    void updatePost(Map<String, Object> postInfo);


    // 설지연 ------------------------------------------------------
    List<PostDTO> postList();

    int likeCheck(String loginId, int contentNo);

    int bookmarkCheck(String loginId, int contentNo);

    List<String> getTag(int contentNo);

    // 댓글 리스트 가져오기
    List<CommentDTO> getCommentList(int postId);


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

    String findUserAccountId(String userNickname);

    // 게시물의 좋아요 정보 가져오기
    List<String> getLikeInfo(int postId);

    // 대댓글의 commentId 가져오기
    List<Integer> getReplyComment(int commentId);

    // 팔로우 메서드
    void follow(Map<String, Object> followInfo);

    // 팔로우한 닉네임 가져오기
    List<String> getfollowList(String loginNickname);

    // 팔로우 취소 메서드
    void deleteFollow(Map<String, Object> followingInfo);

    // 댓글 삭제 메서드
    void deleteComment(int commentId);


}
