package org.example.post.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.post.dto.LikePushDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface PostController {
    // 게시물 업로드 페이지로 이동
    ModelAndView showUploadPost(HttpServletRequest request, HttpServletResponse response) throws Exception;
    // 게시물(이미지) 추가
    void upload(String postContent, MultipartFile[] file, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 설지연 --------------------------------------
    ModelAndView post(HttpServletRequest request, HttpServletResponse response) throws Exception;

    String heartPush(LikePushDTO likePushDTO);
}
