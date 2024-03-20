package org.example.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.member.dto.MemberDTO;
import org.example.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

// 회원 컨트롤러의 구현체
@RestController
public class MemberControllerImpl implements MemberController{
    @Autowired
    private MemberService service;

    @Override
    @PostMapping(value = {"/login.do","member/login.do"})
    public ModelAndView login(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpServletRequest request){
        HttpSession session = request.getSession();
        MemberDTO memberDTO = service.login(userId, userPw);
        if(memberDTO != null) {
            // 사용자 정보가 존재할 때만 세션에 저장
            session.setAttribute("ROLE", memberDTO.getROLE());
            session.setAttribute("accountID", memberDTO.getAccountID());
            // 로그인 성공 시 로그인 성공 테스트 페이지로 리다이렉트
            return new ModelAndView("redirect:/main");
        } else{
            // 홈 페이지로 리다이렉트
            return new ModelAndView("redirect:/?warning=loginFail");
        }
    }
//    @PostMapping(value = "/login.do", produces = "application/json")
//    @ResponseBody
//    public Map<String, Object> login(@RequestParam("userId") String userId,
//                                     @RequestParam("userPw") String userPw,
//                                     HttpSession session) {
//        Map<String, Object> result = new HashMap<>();
//        MemberDTO memberDTO = service.login(userId, userPw);
//        if(memberDTO != null) {
//            session.setAttribute("ROLE", memberDTO.getROLE());
//            session.setAttribute("accountID", memberDTO.getAccountID());
//            result.put("status", "success");
//        } else {
//            result.put("status", "fail");
//        }
//        return result;
//    }

    // 회원가입 페이지로 이동
    @Override
    @RequestMapping("/member/joinMember.do")
    public ModelAndView joinMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("join");
    }

//    // 회원 추가
//    @Override
//    @RequestMapping("/member/addMember.do")
//    public ModelAndView addMember(MemberDTO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        int result = service.addMember(member);
//
//        return new ModelAndView("login");
//    }

    // 회원 추가 ( 프로필 이미지까지 함께 )
    @PostMapping("/member/addMember.do")
    public ModelAndView addMember(@RequestParam("file") MultipartFile file, MemberDTO member, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 웹 접근 가능한 경로 내에 이미지 저장 폴더를 설정
        String saveDirectory = request.getServletContext().getRealPath("/userProfile/");

//        파일명 중복시
//        String originalFileName = file.getOriginalFilename();
//        String fileExtension = Objects.requireNonNull(originalFileName).substring(originalFileName.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString() + fileExtension;
//        String filePath = Paths.get(saveDirectory, newFileName).toString();

        // 폴더 생성 로직을 추가합니다.
        File directory = new File(saveDirectory);
        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs(); // 디렉터리 생성 시도
            if (!isCreated) {
                // 디렉터리 생성 실패에 대한 처리
                // 예: 로깅, 예외 던지기 등
                throw new IOException("Failed to create directory: " + saveDirectory);
            }
        }

        // 파일이 비어있지 않은 경우 처리
        if (!file.isEmpty()) {
            String originalFileName = file.getOriginalFilename(); // 원본 파일 이름
            String filePath = Paths.get(saveDirectory, originalFileName).toString(); // 저장 경로 + 파일 이름

            // 파일 저장
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            // 웹 접근 가능한 상대 경로를 데이터베이스에 저장
            member.setProfileImg(request.getContextPath() + "/userProfile/" + originalFileName);

        }

        // 회원 정보 및 이미지 경로 데이터베이스에 저장
        int result = service.addMember(member);

        // 처리 후 리다이렉트 또는 뷰 반환
        return new ModelAndView("redirect:/");
    }

    // 아이디 중복 확인
    @Override
    @RequestMapping("/member/checkId.do")
    @ResponseBody
    public String checkId(@RequestParam("userId") String userId) {
        boolean isAvailable = service.checkId(userId);
        return isAvailable ? "OK" : "EXIST";
    }

    // 닉네임 중복 확인
    @Override
    @RequestMapping("/member/checkNickname.do")
    @ResponseBody
    public String checkNickname(@RequestParam("userNickname") String userNickname) {
        boolean isAvailable = service.checkNickname(userNickname);
        return isAvailable ? "OK" : "EXIST";
    }

    // 아이디 찾기
    @Override
    @RequestMapping(value = "/findById.do", method = RequestMethod.POST)
    @ResponseBody
    public String findById(@RequestParam("userName") String userName, @RequestParam("userBirth") String userBirth, HttpServletRequest request) {
        String userId = service.findById(userName, userBirth);
        if(userId != null) {
            // 아이디 찾기 성공
            return userId;
        } else {
            // 일치하는 사용자가 없음
            return "not found";
        }
    }

    // 비밀번호 찾기
    @Override
    @RequestMapping(value = "/findByPw.do", method = RequestMethod.POST)
    @ResponseBody
    public String findByPw(@RequestParam("userId") String userId, @RequestParam("userBirth") String userBirth, HttpServletRequest request) {
        boolean isAvailable = service.findByPw(userId, userBirth);
        return isAvailable ? "OK" : "FAIL";
    }

    // 비밀번호 변경 - 입력한 아이디로 식별자 아이디 찾기
    @Override
    @RequestMapping(value = "/findByAccountID_useId", method = RequestMethod.POST)
    @ResponseBody
    public String findByAccountID_useId(@RequestParam("userId") String userId) {
        String accountId = service.findByAccountID_useId(userId);
        if(accountId != null){
            // 식별자 아이디 찾기 성공 - 식별자 아이디 반환
            return accountId;
        } else {
            // 식별자 아이디 찾기 실패
            return null;
        }
    }

    // 비밀번호 변경
    @Override
    @RequestMapping(value = "/changePw.do", method = RequestMethod.POST)
    @ResponseBody
    public String changePw(@RequestParam("changePw") String changePw, @RequestParam("accountId") String accountId, HttpServletRequest request) {
        boolean isAvailable = service.changePw(changePw, accountId);
        return isAvailable ? "SUCCESS" : "FAIL";
    }

    // 로그아웃
    @Override
    @RequestMapping("/logout.do")
    public ModelAndView logout(HttpServletRequest request) {
        // 세션 가져오기
        HttpSession session = request.getSession(false); // false: 존재하지 않으면 null 반환
        if (session != null) {
            // 세션 무효화
            session.invalidate();
        }
        // 홈 페이지로 리다이렉트
        return new ModelAndView("redirect:/");
    }

}
