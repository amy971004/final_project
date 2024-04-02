package org.example.post.controller;

// 필요한 클래스와 인터페이스를 임포트합니다.

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.post.dto.*;
import org.example.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// 클래스 선언부입니다. @Controller 애노테이션을 사용하여 이 클래스가 컨트롤러임을 나타냅니다.
@Controller
@RequestMapping("/main/post")
public class PostControllerImpl implements PostController {
    private static final String IMAGE_PATH = "C:\\project\\post";
    private static final String PROFILE_IMAGE_PATH ="C:\\project\\profile";
    // PostService 타입의 service 객체에 대한 의존성을 주입받습니다.
    // @Autowired 애노테이션을 통해 Spring이 자동으로 주입하게 합니다.
    @Autowired
    private PostService service;

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
    public void upload(String content, MultipartFile[] file,  HttpServletRequest request, HttpServletResponse response) throws Exception{


        // 게시물 정보 저장
        Map<String, Object> postInfo = new HashMap<String, Object>();
        // 이미지에 대한 정보 저장
        List<ImageDTO> imageFileInfo = new ArrayList<ImageDTO>();

        // 게시물 번호 가져오기
        int postNo = service.selectPostId();

        // 지정 경로에 폴더가 없으면 자동생성.
        File directory = new File(IMAGE_PATH + '/' + postNo);
        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs(); // 디렉터리 생성 시도
            if (!isCreated) {
                // 디렉터리 생성 실패에 대한 처리
                // 예: 로깅, 예외 던지기 등
                throw new IOException("Failed to create directory: " + IMAGE_PATH + '/' + postNo);
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
                String path = Paths.get(IMAGE_PATH + '/' + postNo + '/' + fileName).toString();
                // 이미지 객체 생성 / 정보 저장
                ImageDTO image = new ImageDTO();
                image.setFileName(fileName);
                image.setFilePath(path);
                // 이미지 객체를 List에 저장
                imageFileInfo.add(image);

                // 파일에 저장
                File filepath = new File(path);
                multipartFile.transferTo(filepath);
            }
            // id
            HttpSession session = request.getSession();
            String accountID = (String)session.getAttribute("accountID");
            // 회원 닉네임 가져오기
            String account_id = service.selectNickname(accountID);
            // 게시물에 대한 정보 Map에 저장
            postInfo.put("imageFileInfo",imageFileInfo);
            postInfo.put("content", content);
            postInfo.put("userNickname", account_id);

            service.addPost(postInfo);

            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('업로드 성공');");
            out.println("location.href='/main';");
            out.println("</script>");
            out.close();

        } catch (Exception e) {
            // 예외 발생 시 이미지 파일 삭제
            if(imageFileInfo != null && imageFileInfo.size() != 0){
                for(ImageDTO imageDTO : imageFileInfo){
                    Path filePath = Paths.get(IMAGE_PATH + '/' + postNo + '/' + imageDTO.getFileName());
                    Files.delete(filePath);
                    System.out.println(imageDTO.getFileName());
                }
                // 폴더 삭제
                if(directory.exists()){
                    directory.delete();
                }
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('업로드 실패');");
                out.println("history.go(-1)");
                out.println("</script>");
                out.close();
                e.printStackTrace();
            }
        }
    }

    // 설지연 ------------------------------------------------------------

    // 페이지 테스트
    @RequestMapping("/test2.do")
    public ModelAndView test2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("test");
    }

    // 전체 게시물 출력
    @RequestMapping("/mainPost.do")
    public ModelAndView post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 로그인된 계정의 닉네임 가져오기
        HttpSession session = request.getSession();
        String accountId = (String) session.getAttribute("accountID");
        String loginNickname = loginNickname(accountId);

        // 전체 게시물 가져오기
        List<PostDTO> postList = service.postList(); // 기본 게시물 리스트
        List<CommentDTO> commentList = service.getCommentList(); // 전체 댓글 가져오기
        List<ImageDTO> imageList = service.getImageList(); // 전체 이미지 정보 가져오기

        List<TagDTO> tagsList = new ArrayList<>(); //태그 저장하는 리스트
        List<likeBookDTO> likeBookList = new ArrayList<>(); // 좋아요 북마크 저장하는 리스트
        Map<String, Object> postMap = new HashMap<>(); // 게시물 전체정보 저장하는 map

        int contentNo = 0;
        for (PostDTO post : postList) {
            contentNo = post.getPostId();
            likeBookDTO likebook = new likeBookDTO();
            // 좋아요 여부 (0,1)
            likebook.setContentNo(post.getPostId());
            likebook.setLikeCheck(service.likeCheck(loginNickname, contentNo));
            // 좋아요 갯수
            likebook.setLikeCnt(service.likeCnt(contentNo));
            // 북마크 여부 (0,1)
            likebook.setBookmarkCheck(service.bookmarkCheck(loginNickname, contentNo));
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
        postMap.put("postList", postList); // 게시물리스트
        postMap.put("tagsList", tagsList); // 태그리스트
        postMap.put("likeBookList", likeBookList); // 좋아요, 북마크 여부 리스트
        postMap.put("commentList", commentList); // 전체 댓글 리스트
        postMap.put("imageList", imageList); // 전체 이미지 정보
        mav.addObject("postMap", postMap);
        mav.addObject("loginNickname", loginNickname);

        return mav;
    }

    // 로그인된 계정의 닉네임 가져오기
    public String loginNickname(String accountId) {
        String loginNickname = service.loginNickname(accountId);
        return loginNickname;
    }

    // 좋아요 버튼
    @RequestMapping(value = "/heartPush.do", method = RequestMethod.GET)
    @ResponseBody
    public String heartPush(PushDTO pushDTO) {
        String loginNickname = pushDTO.getLoginNickname();
        int postId = pushDTO.getPostId();
        if (pushDTO.getCheck() == 1) {
            int result = service.deletLike(postId, loginNickname);
        } else if (pushDTO.getCheck() == 0) {
            int result = service.pushLike(postId, loginNickname);
        }
        return "success";
    }

    // 북마크 버튼
    @ResponseBody
    @RequestMapping(value = "/bookPush.do", method = RequestMethod.GET)
    public String bookPush(PushDTO pushDTO) {
        String loginNickname = pushDTO.getLoginNickname();
        int postId = pushDTO.getPostId();
        if (pushDTO.getCheck() == 1) {
            int result = service.deletBook(postId, loginNickname);
        } else if (pushDTO.getCheck() == 0) {
            int result = service.pushBook(postId, loginNickname);
        }

        System.out.println(pushDTO.getCheck());
        System.out.println(pushDTO.getLoginNickname());
        System.out.println(pushDTO.getPostId());
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/inputComment.do", method = RequestMethod.GET)
    // 댓글 저장 메서드
    public String inputComment(Comment2DTO comment2DTO) {
        System.out.println(comment2DTO.getComment());
        System.out.println(comment2DTO.getPostId());
        System.out.println(comment2DTO.getLoginNickname());

        //CommentDTO comment = new CommentDTO();
//        comment.setPostComment(comment2DTO.getComment());
//        comment.setPostId(comment2DTO.getPostId());
//        comment.setUser_Nickname(comment2DTO.getLoginNickname());
//        comment.setParentNo(0);
        Map<String, Object> comment = new HashMap<>();
        comment.put("postComment", comment2DTO.getComment());
        comment.put("postId", comment2DTO.getPostId());
        comment.put("loginNickname", comment2DTO.getLoginNickname());
        comment.put("parentNo", 0);

        service.inputComment(comment);


        return "success";
    }

    // POST에 이미지 불러오기
    @RequestMapping("imageDownload.do")
    public void imageDownload(@RequestParam("imageFileName") String imageFileName,
                              @RequestParam("postId") int postId,
                              HttpServletResponse response) throws Exception {
        OutputStream out = response.getOutputStream();
        String downFile = IMAGE_PATH + "\\" + postId + "\\" + imageFileName;
        File file = new File(downFile);

        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Content-disposition",
                "attachment;fileName=" + imageFileName);
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[1024 * 8];
        while (true) {
            int count = in.read(buffer);
            if (count == -1) {
                break;
            }
            out.write(buffer, 0, count);
        }
        in.close();
        out.close();

    }

    // 프로필 이미지 가져오기
    @RequestMapping("profileImageDownload.do")
    public void profileImageDownload(@RequestParam("userNickname") String userNickname,
                              HttpServletResponse response) throws Exception {
        String userId = service.findUserId(userNickname);
        String profileImg = service.getProfileImg(userNickname);
        OutputStream out = response.getOutputStream();
        String downFile = PROFILE_IMAGE_PATH + "\\" + userId + "\\" + profileImg;
        File file = new File(downFile);

        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Content-disposition",
                "attachment;fileName=" + profileImg);
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[1024 * 8];
        while (true) {
            int count = in.read(buffer);
            if (count == -1) {
                break;
            }
            out.write(buffer, 0, count);
        }
        in.close();
        out.close();

    }

    // 게시물 삭제하기
    @RequestMapping(value = "/deletePost.do",method = RequestMethod.POST)
    public ResponseEntity deletePost(
            int postId,
            HttpServletRequest request,
            HttpServletResponse response)throws Exception {
        String message = null;
        ResponseEntity resEnt = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html;charset=utf-8");

        try{
            service.deletePost(postId);
            message = "<script>";
            message += "alert('게시물이 삭제 되었습니다.');";
            message += "location.href='/post/mainPost.do';";
            message += "</script>";
            resEnt = new  ResponseEntity(message, responseHeaders, HttpStatus.OK);
        }catch (Exception e){
            message = "<script>";
            message += "alert('게시물이 삭제되지 않았습니다. 다시 시도해 주세요.');";
            message += "location.href='/post/mainPost.do';";
            message += "</script>";
            resEnt = new  ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
            e.printStackTrace();
        }
        return resEnt;
    }


}
