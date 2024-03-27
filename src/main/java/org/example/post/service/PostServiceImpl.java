package org.example.post.service;

import org.example.post.dao.PostDAO;
import org.example.post.dto.CommentDTO;
import org.example.post.dto.ImageDTO;
import org.example.post.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<CommentDTO> getCommentList() {
        return dao.getCommentList();
    }

    @Override
    public int likeCnt(int contentNo) {
        return dao.likeCnt(contentNo);
    }

    @Override
    public int deletLike(int postId, String loginNickname) {
        return dao.deletLike(postId, loginNickname);
    }

    @Override
    public int pushLike(int postId, String loginNickname) {
        return dao.pushLike(postId, loginNickname);
    }
}
