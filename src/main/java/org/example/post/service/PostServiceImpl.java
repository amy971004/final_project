package org.example.post.service;

import org.example.post.dao.PostDAO;
import org.example.post.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostDAO dao;

    @Override
    public int addpost(Map<String, Object> postMap) {
        int contentNo = dao.selectContentNo();
        postMap.put("contentNo", contentNo);
        int result = dao.addPost(postMap);

        List<ImageDTO> imageFileList = (List<ImageDTO>) postMap.get("imageFileList");
        int imageNo = dao.selectImageNo();

        for(ImageDTO imageDTO : imageFileList) {
            imageDTO.setImageNo(++imageNo);
            imageDTO.setContentNo(contentNo);
        }
        dao.addImage(imageFileList);

        return result;
    }
}
