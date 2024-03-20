package org.example.uploadpost.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface PostController {
    // 게시물 업로드 페이지로 이동
    ModelAndView showUploadPost(HttpServletRequest request, HttpServletResponse response) throws Exception;
    // 게시물(이미지) 추가
    ModelAndView upload(String postContent, MultipartFile[] file, HttpServletRequest request) throws Exception;
}
