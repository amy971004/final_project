package org.example.post.dao;

import org.example.post.dto.CommentDTO;
import org.example.post.dto.ImageDTO;
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

    // 댓글 리스트 가져오기
    List<CommentDTO> getCommentList(int postId);

    int likeCnt(int contentNo);

    int deletLike(int postId, String loginNickname);

    int pushLike(int postId, String loginNickname);

    // SNS_LIKE 테이블의  likeId 생성
    int selectNewLikeId();

    int selectNewBookmarkId();

    // 로그인된 계정의 닉네임 가져오기
    String loginNickname(String accountId);

    // 북마크 취소
    int deleBook(int postId, String loginNickname);

    // 북마크 저장
    int pushBook(int postId, String loginNickname);

    // 댓글 COMMENTID 가져오기
    int getCommentId();

    // 댓글 저장하기
    void inputComment(Map<String,Object> comment);

    // 이미지리스트 가져오기
    List<ImageDTO> getImageList();

    // 게시물 삭제
    void deletePost(int postId);

    // 프로필 이미지 가져오기
    String getProfileImg(String userNickname);

    // 게시물 좋아요 정보 가져오기
    List<String> getLikeInfo(int postId);

    // 대댓글의 commentId 가져오기
    List<Integer> getReplyComment(int commentId);

    // 팔로우 메서드
    void follow(Map<String, Object> followInfo);

    // 팔로우한 닉네임 가져오기
    List<String> getfollowList(String loginNickname);

    // 팔로우 취소 메서드
    void delteFollow(Map<String, Object> followingInfo);

    String findUserAccountId(String userNickname);

    // 댓글 삭제 메서드
    void deleteComment(int commentId);
}
