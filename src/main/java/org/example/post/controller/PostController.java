package org.example.post.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.post.dto.PushDTO;
import org.example.post.dto.TagDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

public interface PostController {
    // 게시물 업로드 페이지로 이동
    ModelAndView showUploadPost(HttpServletRequest request, HttpServletResponse response) throws Exception;
    // 게시물(이미지) 추가
    void upload(String content, MultipartFile[] file, String hashTags, HttpServletRequest request, HttpServletResponse response) throws Exception;
    // 해당 게시물 불러오기
    ModelAndView postDetail (int postId, HttpServletRequest request, HttpServletResponse response)throws Exception;
    // 게시물 수정
    ResponseEntity update (String content, String hashTags, int postId, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 설지연 --------------------------------------
    ModelAndView post(HttpServletRequest request, HttpServletResponse response) throws Exception;

    String heartPush(PushDTO likePushDTO);
}
