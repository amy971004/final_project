package org.example.post.service;

import org.example.post.dto.CommentDTO;
import org.example.post.dto.PostDTO;

import java.util.List;
import java.util.Map;

public interface PostService {

    void addPost(Map<String, Object> postInfo);

    // 설지연 ------------------------------------------------------
    List<PostDTO> postList();

    int likeCheck(String loginId, int contentNo);

    int bookmarkCheck(String loginId, int contentNo);

    List<String> getTag(int contentNo);

    List<CommentDTO> getCommentList();

    int likeCnt(int contentNo);

    int deletLike(int postId, String loginNickname);

    int pushLike(int postId, String loginNickname);
}
