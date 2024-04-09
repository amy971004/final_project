package org.example.post.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.post.dto.CommentDTO;
import org.example.post.dto.ImageDTO;
import org.example.post.dto.PostDTO;
import org.example.post.dto.TagDTO;
import org.example.profile.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostDAOImpl implements PostDAO {
    @Autowired
    private SqlSession sqlSession;

    // 게시물 정보 저장
    @Override
    public void addPost(Map<String, Object> postInfo) {
        sqlSession.insert("mapper.post.addPost", postInfo);
    }
    // 게시물 정보 저장
    @Override
    public void addImage(List<ImageDTO> imageFileInfo) {
        sqlSession.insert("mapper.post.addImage", imageFileInfo);
    }
    // 해시 태그 저장
    @Override
    public void addTag(List<Map<String, Object>> tagInfo) {
        sqlSession.insert("mapper.post.addTag", tagInfo);
    }
    // 게시물 번호 가져오기
    @Override
    public int selectPostId() {
        return sqlSession.selectOne("mapper.post.selectPostId");
    }
    // 이미지 번호 가져오기
    @Override
    public int selectImageNo() {
        return sqlSession.selectOne("mapper.post.selectImageNo");
    }
    // 닉네임 가져오기
    @Override
    public String selectNickname(String accountID) {
        return sqlSession.selectOne("mapper.post.selectNickname", accountID);
    }
    // 프로필 가져오기
    @Override
    public ProfileDTO selectProfile(String accountId) {
        return sqlSession.selectOne("mapper.profile.profileView", accountId);
    }
    // 해당 게시물 정보 가져오기
    @Override
    public PostDTO getPost(int postId) {
        return sqlSession.selectOne("mapper.post.getPost",postId);
    }
    // 해당 이미지 정보 가져오기
    @Override
    public List<ImageDTO> getImage(int postId) {
        return sqlSession.selectList("mapper.post.getImage", postId);
    }
    // 게시물 수정
    @Override
    public void updatePost(Map<String, Object> postInfo) {
        sqlSession.update("mapper.post.updatePost", postInfo);
    }
    // 해시 태그 삭제


    // 설지연 -------------------------------------------------------------


    @Override
    public List<PostDTO > postList() {
        return sqlSession.selectList("mapper.post.postList");
    }

    // 좋아요 여부
    @Override
    public int likeCheck(String loginId, int contentNo) {
        Map<String,Object> data = new HashMap<>();
        data.put("loginId",loginId);
        data.put("contentNo",contentNo);
        return sqlSession.selectOne("mapper.post.likeCheck",data);
    }

    @Override
    public int bookmarkCheck(String loginId, int contentNo) {
        Map<String,Object> data = new HashMap<>();
        data.put("loginId",loginId);
        data.put("contentNo",contentNo);
        return sqlSession.selectOne("mapper.post.bookmarkCheck",data);
    }

    @Override
    public List<String> getTag(int contentNo) {
        return sqlSession.selectList("mapper.post.getTag",contentNo);
    }

    // 댓글 리스트 가져오기
    @Override
    public List<CommentDTO> getCommentList(int postId) {

        return sqlSession.selectList("mapper.post.getCommentList",postId);
    }

    @Override
    public int likeCnt(int contentNo) {
        return sqlSession.selectOne("mapper.post.likeCnt",contentNo);
    }

    @Override
    public int deletLike(int postId, String loginNickname) {
        Map<String,Object> data = new HashMap<>();
        data.put("postId",postId);
        data.put("loginNickname",loginNickname);
        return sqlSession.delete("mapper.post.deletLike",data);
    }

    // 좋아요 저장
    @Override
    public int pushLike(int postId, String loginNickname) {
        Map<String,Object> data = new HashMap<>();
        int newLikeNo = selectNewLikeId();
        data.put("newLikeId",newLikeNo+1);
        data.put("postId",postId);
        data.put("loginNickname",loginNickname);
        return sqlSession.insert("mapper.post.pushLike",data);
    }
    // 좋아요 식별자 번호
    @Override
    public int selectNewLikeId() {
        return sqlSession.selectOne("mapper.post.selectNewLikeId");
    }
    // 북마크 식별자 번호
    @Override
    public int selectNewBookmarkId() {
        return sqlSession.selectOne("mapper.post.selectNewBookmarkId");
    }

    // 로그인된 계정의 닉네임 가져오기
    @Override
    public String loginNickname(String accountId) {
        return sqlSession.selectOne("mapper.post.selectLoginNickname", accountId);
    }

    // 북마크 취소
    @Override
    public int deleBook(int postId, String loginNickname) {
        Map<String,Object> data = new HashMap<>();
        data.put("postId",postId);
        data.put("loginNickname",loginNickname);
        return sqlSession.delete("mapper.post.deletBook",data);
    }

    // 북마크 저장
    @Override
    public int pushBook(int postId, String loginNickname) {
        Map<String,Object> data = new HashMap<>();
        int newBookmarkNo = selectNewBookmarkId();
        data.put("newBookmarkId",newBookmarkNo+1);
        data.put("postId",postId);
        data.put("loginNickname",loginNickname);
        return sqlSession.insert("mapper.post.pushBook",data);
    }

    @Override
    public int getCommentId() {
        return sqlSession.selectOne("mapper.post.getCommentId");
    }

    @Override
    public void inputComment(Map<String,Object> comment) {
        sqlSession.insert("mapper.post.inputComment",comment);
    }

    @Override
    public List<ImageDTO> getImageList() {
        return sqlSession.selectList("mapper.post.getImageList");
    }

    // 게시물 삭제
    @Override
    public void deletePost(int postId) {
        sqlSession.delete("mapper.post.deletePost",postId);
    }

    // 프로필 이미지이름 가져오기
    @Override
    public String getProfileImg(String userNickname) {
        return sqlSession.selectOne("mapper.post.getProfileImg",userNickname);
    }

    // 게시물의 좋아요 정보 가져오기
    @Override
    public List<String> getLikeInfo(int postId) {
        return sqlSession.selectList("mapper.post.getLikeInfo",postId);
    }

    // 대댓글의 commentId 가져오기
    @Override
    public List<Integer> getReplyComment(int commentId) {
        return sqlSession.selectList("mapper.post.getReplyComment",commentId);
    }

    // 팔로우 아이디 가져오기
    private int selectNewfollowId() {
        return sqlSession.selectOne("mapper.post.selectNewfollowId");
    }

    // 팔로우 메서드
    @Override
    public void follow(Map<String, Object> followInfo) {
        int followId = selectNewfollowId();
        followInfo.put("followId",followId+1);
        sqlSession.insert("mapper.post.follow",followInfo);
    }

    @Override
    public List<String> getfollowList(String loginNickname) {
        return sqlSession.selectList("mapper.post.getfollowList",loginNickname);
    }

    // 팔로우 취소 메서드
    @Override
    public void delteFollow(Map<String, Object> followingInfo) {
        sqlSession.delete("mapper.post.deleteFollow",followingInfo);
    }

    @Override
    public void deleteComment(int commentId) {
        sqlSession.delete("mapper.post.deleteComment",commentId);
    }

    @Override
    public void delTag(int postId) {
        sqlSession.delete("mapper.post.delTag", postId);
    }


    @Override
    public String findUserAccountId(String userNickname) {
        return sqlSession.selectOne("mapper.profile.findUserAccountId", userNickname);
    }

}
