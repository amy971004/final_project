package org.example.post.dao;

import org.example.post.dto.ImageDTO;

import java.util.List;
import java.util.Map;

public interface PostDAO {
    int selectContentNo();

    int addPost(Map<String, Object> postMap);

    int selectImageNo();

    void addImage(List<ImageDTO> imageFileList);
}
