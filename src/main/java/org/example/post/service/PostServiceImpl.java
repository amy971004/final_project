package org.example.post.service;

import org.example.post.dao.PostDAO;
import org.example.post.dto.CommentDTO;
import org.example.post.dto.ImageDTO;
import org.example.post.dto.PostDTO;
import org.example.profile.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO dao;

    @Override
    public void addPost(Map<String, Object> postInfo) {
        // 게시물 번호 가져오기
        int postId = dao.selectPostId();
        System.out.println("가져온 게시물 번호 : " + postId);
        // 이미지에 대한 정보 가져오기
        List<ImageDTO> imageFileInfo = (List<ImageDTO>) postInfo.get("imageFileInfo");
        // 이미지 번호 가져오기
        int imageNo = dao.selectImageNo();
        System.out.println("가져온 이미지 번호 : "+ imageNo);
        // 각 이미지에 게시물 번호 / 이미지 번호 저장
        for(ImageDTO imageDTO : imageFileInfo){
            imageDTO.setPostId(postId);
            imageDTO.setImageNo(imageNo++);
            System.out.println("게시물 번호 : " + imageDTO.getPostId());
            System.out.println("이미지 번호 : " + imageDTO.getImageNo());
        }
        // 게시물 정보 추가
        dao.addPost(postInfo);
        // 이미지 정보 추가
        dao.addImage(imageFileInfo);
    }

    // 회원 닉네임 가져오기
    @Override
    public String selectNickname(String accountID) {
        return dao.selectNickname(accountID);
    }

    // 게시물 번호 가져오기
    @Override
    public int selectPostId() {
        return dao.selectPostId();
    }

    // 프로필 가져오기
    @Override
    public ProfileDTO selectProfile(String accountId) {
        return dao.selectProfile(accountId);
    }

    // 설지연 -------------------------------------------------------
    @Override
    public List<PostDTO> postList() {

        return dao.postList();
    }

    @Override
    public int likeCheck(String loginId, int contentNo) {

        return dao.likeCheck(loginId, contentNo);
    }

    @Override
    public int bookmarkCheck(String loginId, int contentNo) {

        return dao.bookmarkCheck(loginId, contentNo);
    }

    @Override
    public List<String> getTag(int contentNo) {
        return dao.getTag(contentNo);
    }


    // 댓글 리스트 가져오기
    @Override
    public List<CommentDTO> getCommentList(int postId) {
        return dao.getCommentList(postId);
    }

    @Override
    public int likeCnt(int contentNo) {
        return dao.likeCnt(contentNo);
    }

    // 좋아요 취소
    @Override
    public int deletLike(int postId, String loginNickname) {
        return dao.deletLike(postId, loginNickname);
    }

    //좋아요 저장
    @Override
    public int pushLike(int postId, String loginNickname) {
        return dao.pushLike(postId, loginNickname);
    }

    // 로그인된 계정의 닉네임 가져오는 메서드
    @Override
    public String loginNickname(String accountId) {
        return dao.loginNickname(accountId);
    }

    // 북마크 취소버튼
    @Override
    public int deletBook(int postId, String loginNickname) {
        return dao.deleBook(postId, loginNickname);
    }

    // 북마크 저장버튼
    @Override
    public int pushBook(int postId, String loginNickname) {
        return dao.pushBook(postId, loginNickname);
    }

    // 댓글 저장
    @Override
    public void inputComment(Map<String,Object> comment) {
        // 댓글 식별자 번혹 가져오기
        int commentId = dao.getCommentId();
        comment.put("commentId",commentId+1);
        dao.inputComment(comment);
    }

    // 이미지 리스트 가져오기
    @Override
    public List<ImageDTO> getImageList() {
        return dao.getImageList();
    }

    // 게시물 삭제
    @Override
    public void deletePost(int postId) {
        dao.deletePost(postId);
    }

    // 프로필 이미지 이름 가져오기
    @Override
    public String getProfileImg(String userNickname) {
        return dao.getProfileImg(userNickname);
    }

    // 게시물의 좋아요 정보 가져오기
    @Override
    public List<String> getLikeInfo(int postId) {
        return dao.getLikeInfo(postId);
    }

    // 대댓글의 commentId 가져오기
    @Override
    public List<Integer> getReplyComment(int commentId) {
        return dao.getReplyComment(commentId);
    }

    // 팔로우 메서드
    @Override
    public void follow(Map<String, Object> followInfo) {
        dao.follow(followInfo);
    }

    @Override
    public List<String> getfollowList(String loginNickname) {
        return dao.getfollowList(loginNickname);
    }

    // 팔로우 취소 메서드
    @Override
    public void deleteFollow(Map<String, Object> followingInfo) {
        dao.delteFollow(followingInfo);

    }

    @Override
    public String findUserAccountId(String userNickname) {
        return dao.findUserAccountId(userNickname);
    }

    // 댓글 삭제 메서드
    @Override
    public void deleteComment(int commentId) {
        dao.deleteComment(commentId);
    }

    // 북마크된 postId 가져오기
    @Override
    public List<Integer> getBookMarkPostId(String loginNickname) {
        return dao.getBookMarkPostId(loginNickname);
    }

    // 저장된 파일의 첫번째 이미지이름 가져오기
    @Override
    public String getFirstFileName(int postId) {
        return dao.getFirstFileName(postId);
    }
    // 게시물의 댓글 수 가져오기
    @Override
    public int getCommentCnt(int postId) {
        return dao.getCommentCnt(postId);
    }

    @Override
    public List<ImageDTO> getPostImage(List<Integer> bookMarkPostId) {
        return dao.getPostImage(bookMarkPostId);
    }

    @Override
    public String getUserNickname(int postId) {
        return dao.getUserNickname(postId);
    }

    @Override
    public Date getWriteDate(int postId) {
        return dao.getWriteDate(postId);
    }

    @Override
    public int getBookMarkId(String loginNickname, int postId) {
        return dao.getBookMarkId(loginNickname, postId);
    }

    @Override
    public void bookMarkCancle(int bookMarkId) {
        dao.bookMarkCancle(bookMarkId);
    }

    @Override
    public List<PostDTO> follow_postList(List<String> followList) {
        return dao.follow_postList(followList);
    }

    @Override
    public void followCancle(Map<String, Object> followInfo) {
        dao.followCancle(followInfo);
    }


}
