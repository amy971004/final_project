package org.example.post.service;

import org.example.post.dao.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostDAO dao;


    @Override
    public void addPost(Map<String, Object> postInfo) {
        dao.addFile(postInfo); // DAO 메서드 호출
    }
}
