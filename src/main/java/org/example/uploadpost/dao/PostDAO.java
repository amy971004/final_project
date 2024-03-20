package org.example.uploadpost.dao;

import org.example.uploadpost.dto.ImageDTO;

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
}
