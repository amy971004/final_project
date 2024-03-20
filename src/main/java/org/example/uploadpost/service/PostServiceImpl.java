package org.example.uploadpost.service;

import org.example.uploadpost.dao.PostDAO;
import org.example.uploadpost.dto.ImageDTO;
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
        // 게시물 정보 추가
        dao.addPost(postInfo);
        System.out.println(postId);
        // 이미지에 대한 정보 가져오기
        List<ImageDTO> imageFileInfo = (List<ImageDTO>) postInfo.get("imageFileInfo");
        // 이미지 번호 가져오기
        int imageNo = dao.selectImageNo();
        System.out.println("이미지 번호 : "+imageNo);
        // 각 이미지에 게시물 번호 / 이미지 번호 저장
        for(ImageDTO imageDTO : imageFileInfo){
            imageDTO.setPostId(postId);
            imageDTO.setImageNo(++imageNo);
            System.out.println("이미지 번호" + imageDTO.getImageNo());
        }
        // 이미지 정보 추가
        dao.addImage(imageFileInfo);
    }
}
