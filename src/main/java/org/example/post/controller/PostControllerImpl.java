package org.example.post.controller;

// 필요한 클래스와 인터페이스를 임포트합니다.
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.post.dto.ImageDTO;
import org.example.post.service.PostService;
import org.example.post.dto.*;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// 클래스 선언부입니다. @Controller 애노테이션을 사용하여 이 클래스가 컨트롤러임을 나타냅니다.
@Controller
@RequestMapping("/post")
public class PostControllerImpl implements PostController {
    private String loginId = "mimi1";
    // PostService 타입의 service 객체에 대한 의존성을 주입받습니다.
    // @Autowired 애노테이션을 통해 Spring이 자동으로 주입하게 합니다.
    @Autowired
    private PostService service;

    // 좋아요 버튼
    @RequestMapping(value = "/heartPush.do", method = RequestMethod.GET)
    @ResponseBody
    public String heartPush(LikePushDTO likePushDTO){
        String loginNickname = likePushDTO.getLoginNickname();
        int postId = likePushDTO.getPostId();
        if(likePushDTO.getLike() == 1){
            int result = service.deletLike(postId,loginNickname);
        }else if(likePushDTO.getLike() == 0) {
            int result = service.pushLike(postId,loginNickname);
        }
        System.out.println(likePushDTO.getLike());
        System.out.println(likePushDTO.getLoginNickname());
        System.out.println(likePushDTO.getPostId());
        return "success";
    }

    // 게시물 업로드 페이지로 이동
    // @GetMapping 애노테이션을 사용하여 HTTP GET 요청을 "/post/postUpload" 경로에 매핑합니다.
    @RequestMapping("/uploadPost.do")
    public ModelAndView showUploadPost(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 게시물 업로드 폼이 있는 "uploadPost.jsp" 페이지로 이동
        return new ModelAndView("uploadPost");
    }

    // 파일을 업로드하는 요청을 처리합니다.
    // @PostMapping 애노테이션을 사용하여 HTTP POST 요청을 "/upload" 경로에 매핑합니다.
    @PostMapping("/upload.do")
    public ModelAndView upload(String content, MultipartFile[] file, HttpServletRequest request) throws Exception{

        // 웹 접근 가능한 경로 내에 이미지 저장 폴더를 설정
        String saveDirectory = request.getServletContext().getRealPath("/post/");
        String saveDirectory2 = saveDirectory.replace("ROOT\\", "post\\");

        // 게시물 정보 저장
        Map<String, Object> postInfo = new HashMap<String, Object>();
        // 이미지에 대한 정보 저장
        List<ImageDTO> imageFileInfo = new ArrayList<ImageDTO>();

        // 지정 경로에 폴더가 없으면 자동생성.
        File directory = new File(saveDirectory2);
        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs(); // 디렉터리 생성 시도
            if (!isCreated) {
                // 디렉터리 생성 실패에 대한 처리
                // 예: 로깅, 예외 던지기 등
                throw new IOException("Failed to create directory: " + saveDirectory2);
            }
        }

        try {
            for (MultipartFile multipartFile : file) {
                // 업로드 한 파일 이름
                String fileName = multipartFile.getOriginalFilename();
                // 파일 이름 중복 방지
                UUID uuid = UUID.randomUUID();
                fileName = uuid + "_" + fileName;
                // 파일 저장 경로 정의
                Path path = Paths.get(saveDirectory2 + fileName);
                // 파일에 저장
                Files.write(path, multipartFile.getBytes());
                // 이미지 객체 생성 / 정보 저장
                ImageDTO image = new ImageDTO();
                image.setFileName(fileName);
                image.setFilePath(path.toString());
                // 이미지 객체를 List에 저장
                imageFileInfo.add(image);
            }
            // id
            HttpSession session = request.getSession();
            String userNickname = (String)session.getAttribute("accountID");
            // 게시물에 대한 정보 Map에 저장
            postInfo.put("imageFileInfo",imageFileInfo);
            postInfo.put("content", content);
            postInfo.put("userNickname", userNickname);
            service.addPost(postInfo);

            return new ModelAndView("redirect:/uploadSuccess");
        } catch (Exception e) {
            // 예외 발생 시 이미지 파일 삭제
            if(imageFileInfo != null && imageFileInfo.size() != 0){
                for(ImageDTO imageDTO : imageFileInfo){
                    Path filePath = Paths.get(saveDirectory, imageDTO.getFileName());
                    Files.delete(filePath);
                    System.out.println(imageDTO.getFileName());
                }
                e.printStackTrace();
                return new ModelAndView("redirect:/uploadFailure");
            }
        }
        return new ModelAndView("redirect:/uploadFailure");
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

    // 설지연 ------------------------------------------------------------

    // 전체 게시물 출력
    @RequestMapping("/mainPost.do")
    public ModelAndView post(HttpServletRequest request, HttpServletResponse response) throws Exception {
       // HttpSession session = request.getSession();
        // String loginNickname = sessio

        // 전체 게시물 가져오기
        List<PostDTO> postList = service.postList(); // 기본 게시물 리스트
        List<CommentDTO> commentList = service.getCommentList(); // 전체 댓글 가져오기

        List<TagDTO> tagsList = new ArrayList<>(); //태그 저장하는 리스트
        List<likeBookDTO> likeBookList = new ArrayList<>(); // 좋아요 북마크 저장하는 리스트
        Map<String, Object> postMap = new HashMap<>(); // 게시물 전체정보 저장하는 map

        int contentNo = 0;
        for(PostDTO post : postList){
            contentNo = post.getPostId();
            likeBookDTO likebook = new likeBookDTO();
            // 좋아요 여부 (0,1)
            likebook.setContentNo(post.getPostId());
            likebook.setLikeCheck(service.likeCheck(loginId,contentNo));
            // 좋아요 갯수
            likebook.setLikeCnt(service.likeCnt(contentNo));
            // 북마크 여부 (0,1)
            likebook.setBookmarkCheck(service.bookmarkCheck(loginId,contentNo));
            likeBookList.add(likebook);

            // 태그 가져오기
            List<String> tag = service.getTag(contentNo);
            TagDTO tagDTO = new TagDTO();
            tagDTO.setPostId(contentNo);
            tagDTO.setHashTag(tag);
            tagsList.add(tagDTO);


        }
        ModelAndView mav = new ModelAndView("mainPost");
        //postMap.put("tagsMap",tagsMap);
        postMap.put("postList",postList); // 게시물리스트
        postMap.put("tagsList",tagsList); // 태그리스트
        postMap.put("likeBookList",likeBookList); // 좋아요, 북마크 여부 리스트
        postMap.put("commentList",commentList); // 전체 댓글 리스트
        mav.addObject("postMap",postMap);
        mav.addObject("LoginId",loginId);

        return mav;
    }


}
