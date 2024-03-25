package org.example.profile.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.example.profile.dto.ProfileDTO;
import org.example.profile.service.ProfileService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
public class ProfileControllerImpl implements ProfileController{
    @Autowired
    private ProfileService service;

    // 프로필 정보 띄우기
    @Override
    @RequestMapping("/profile/profileView.do")
    public ModelAndView profileView(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
        HttpSession session = request.getSession();
        ProfileDTO dto = service.profileView((String) session.getAttribute("accountID"));
        ModelAndView mav = new ModelAndView("profile");
        mav.addObject("profile", dto);
        return mav;
    }

    // 프로필 정보 변경 페이지
    @Override
    @RequestMapping("/profile/modprofile.do")
    public ModelAndView modprofile( HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        ProfileDTO dto = new ProfileDTO();
        dto = service.profileView((String) session.getAttribute("accountID"));
        ModelAndView mav = new ModelAndView("modprofile");
        mav.addObject("modprofile", dto);
        mav.addObject("id", dto.getUserId());
        return mav;
    }

    // 아이디 중복 확인
    @Override
    @RequestMapping("/profile/checkId.do")
    @ResponseBody
    public String checkId(String userId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ProfileDTO dto = service.profileView((String) session.getAttribute("accountID"));
        boolean isAvailable = service.checkId(userId, dto.getUserId());
        return isAvailable ? "OK" : "EXIST";
    }

    // 닉네임 중복 확인
    @Override
    @RequestMapping("/profile/checkNickname.do")
    @ResponseBody
    public String checkNickname(String nickname, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        ProfileDTO dto = service.profileView((String) session.getAttribute("accountID"));
        boolean isAvailable = service.checkNickname(nickname, dto.getUserNickname());
        return isAvailable ? "OK" : "EXIST";
    }

    // 프로필 정보 수정
    @Override
    @RequestMapping("/profile/updateProfile.do")
    public ModelAndView updateProfile(ProfileDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        dto.setAccountId((String) session.getAttribute("accountID"));
        String hashedPw = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        dto.setPassword(hashedPw);
        int result = service.updateProfile(dto);
        return  new ModelAndView("redirect:/profile/profileView.do");

    }

    // 이미지,소개 수정 페이지
    @RequestMapping("/profile/editImg.do")
    @Override
    public ModelAndView editImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("id");
        ModelAndView mav = new ModelAndView("editImg");
        mav.addObject("userId", userId);
        return mav;

    }

    // 프로필 이미지와 소개 변경
    @Override
    @PostMapping("/profile/upload.do")
    public ModelAndView upload(String content, MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String accountId = (String) session.getAttribute("accountID");
        String fileName = "";
        ModelAndView mav = new ModelAndView("redirect:/profile/profileView.do");

        try {
            // editImg.jsp 에서 선택된 파일이 있다면 메서드 실행
            if (!file.isEmpty()) {
                fileName = uploadFile(file, accountId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProfileDTO dto = new ProfileDTO();
        dto.setAccountId(accountId);
        dto.setProfileImg(fileName);
        dto.setIntroduction(content);

        int result = service.upload(dto); // 서비스 메서드 호출 시 변경될 수 있음

        if (result >= 1) {
            mav.addObject("msg", "success");
        } else {
            mav.addObject("msg", "fail");
        }

        return mav;
    }

    private String uploadFile(MultipartFile file, String accountId) throws IOException {
        String fileName = "";
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String UPLOAD_DIR = "E:\\profile\\" + accountId + "\\";
        Path directory = Paths.get(UPLOAD_DIR);
        // 경로에 사용자의 accountId로 하는 디렉터리가 없다면 디렉터리 생성
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
        fileName = UUID.randomUUID().toString() + fileExtension;
        Path path = Paths.get(UPLOAD_DIR + fileName);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        Files.write(path, file.getBytes());
        return fileName;
    }

    private static final String BOARD_REPO = "E:\\profile";

    @Override
    @RequestMapping("/profile/download.do")
    public void download(String imageFileName,
                         String accountId,
                         HttpServletResponse response) throws Exception{
        OutputStream out = response.getOutputStream();
        String downFile = BOARD_REPO + "\\" + accountId + "\\" + imageFileName;
        File file = new File(downFile);

        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Content-disposition", "attachment;fileName="+imageFileName);
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[1024 * 8];
        while(true) {
            int count = in.read(buffer);
            if (count == -1) {
                break;
            }
            out.write(buffer, 0, count);
        }
        in.close();
        out.close();
    }
}



