package org.example.post.controller;

// 필요한 클래스와 인터페이스를 임포트합니다.
import org.example.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

// 클래스 선언부입니다. @Controller 애노테이션을 사용하여 이 클래스가 컨트롤러임을 나타냅니다.
@Controller
public class PostControllerImpl implements PostController{

    // PostService 타입의 service 객체에 대한 의존성을 주입받습니다.
    // @Autowired 애노테이션을 통해 Spring이 자동으로 주입하게 합니다.
    @Autowired
    private PostService service;

    // 파일 저장 경로를 문자열 상수로 지정합니다.
    // 여기서는 특정 사용자의 디렉토리 아래의 uploaded_files 폴더를 지정하고 있습니다.
    private final String UPLOAD_DIR = "C:\\Users\\sunzun\\Desktop\\Dev\\WAS\\tomcat_10.1.19\\apache-tomcat-10.1.19\\webapps\\uploaded_files\\";

    // 파일 업로드 페이지를 보여주는 요청을 처리합니다.
    // @GetMapping 애노테이션을 사용하여 HTTP GET 요청을 "/file-upload" 경로에 매핑합니다.
    @GetMapping("/file-upload")
    public String showFileUploadForm() {
        // 파일 업로드 폼이 있는 "file-upload.jsp" 페이지로 이동합니다.
        return "file-upload";
    }

    // 파일을 업로드하는 요청을 처리합니다.
    // @PostMapping 애노테이션을 사용하여 HTTP POST 요청을 "/upload" 경로에 매핑합니다.
    @PostMapping("/upload")
    public String uploadPost(String content, MultipartFile file) {
        if (!file.isEmpty() && content != null && !content.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(UPLOAD_DIR + fileName);
                Files.write(path, file.getBytes());

                // 추가 정보를 포함시킵니다.
                Map<String, Object> postInfo = new HashMap<>();
                postInfo.put("content", content);
                postInfo.put("fileName", fileName);
                postInfo.put("filePath", path.toString());
                postInfo.put("fileSize", file.getSize());
                postInfo.put("uploadTime", new java.util.Date()); // 현재 시간
                postInfo.put("mimeType", file.getContentType());

                service.addPost(postInfo); // 서비스 메서드 호출 시 변경될 수 있음

                return "redirect:/uploadSuccess";
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/uploadFailure";
            }
        } else {
            return "redirect:/uploadFailure";
        }
    }

    // 파일 업로드에 성공했을 때 호출되는 메서드입니다.
    @GetMapping("/uploadSuccess")
    public String uploadSuccess() {
        // "uploadSuccess.jsp" 페이지로 리디렉트합니다.
        return "uploadSuccess";
    }

    // 파일 업로드에 실패했을 때 호출되는 메서드입니다.
    @GetMapping("/uploadFailure")
    public String uploadFailure() {
        // "uploadFailure.jsp" 페이지로 리디렉트합니다.
        return "uploadFailure";
    }

}
