package org.example.post.controller;

import org.example.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


// 회원 컨트롤러의 구현체
@Controller
public class PostControllerImpl implements PostController{


    @Autowired
    private PostService service;

    @GetMapping("/file-upload")
    public String showFileUploadForm() {
        return "file-upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            // 파일 처리 로직 구현
            return "redirect:uploadSuccess";
        } else {
            return "redirect:uploadFailure";
        }
    }

    @GetMapping("/uploadSuccess")
    public String uploadSuccess() {
        // 여기에 필요한 로직 추가
        // 예를 들어, 파일 업로드 성공 페이지를 보여줄 수 있습니다.
        return "uploadSuccess"; // uploadSuccess.jsp 페이지로 리디렉트
    }

}
