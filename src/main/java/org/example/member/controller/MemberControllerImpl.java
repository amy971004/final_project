package org.example.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.member.dto.MemberDTO;
import org.example.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// 회원 컨트롤러의 구현체
@RestController
public class MemberControllerImpl implements MemberController{

    private final MemberService service;

    @Autowired
    public MemberControllerImpl(MemberService service) {
        this.service = service;
    }

    // 로그인
    // 아이디 비밀번호를 입력받아 로그인
    @Override
    @PostMapping(value = {"/login.do","member/login.do"})
    public ModelAndView login(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpServletRequest request) {
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

    // 회원가입 페이지로 이동
    @Override
    @RequestMapping("/member/joinMember.do")
    public ModelAndView joinMember(){return new ModelAndView("join");}

    // 회원가입
    // 가입정보를 입력받은 후 회원가입
    @Override
    @PostMapping("/member/addMember.do")
    public ModelAndView addMember(@RequestParam("file") MultipartFile file,@RequestParam("userId") String userId, MemberDTO member, HttpServletRequest request) throws Exception {

        // 웹 접근 가능한 경로 내에 이미지 저장 폴더를 설정
        String saveDirectory = "C:\\profile\\" + member.getUserId() + "\\";
//        String saveDirectory2 = saveDirectory.replace("ROOT\\", "userProfile\\");

//        파일명 중복시
//        String originalFileName = file.getOriginalFilename();
//        String fileExtension = Objects.requireNonNull(originalFileName).substring(originalFileName.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString() + fileExtension;
//        String filePath = Paths.get(saveDirectory, newFileName).toString();

        // 폴더 생성 로직을 추가합니다.
        Path directory = Paths.get(saveDirectory);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(saveDirectory + fileName);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        Files.write(path, file.getBytes());
        member.setProfileImg(fileName);


        // 회원 정보 및 이미지 경로 데이터베이스에 저장
        int result = service.addMember(member);

        // 처리 후 리다이렉트 또는 뷰 반환
        return new ModelAndView("redirect:/");
    }

    // 회원가입 유효성 검증 (아이디 중복 확인) - javascript - JQuery AJAX
    // 회원 ID중 입력받은 ID와 일치하는 정보가 있으면 (이미 사용중이면) false, 없으면 (사용 가능하면) true
    @Override
    @ResponseBody
    @RequestMapping("/member/checkId.do")
    public String checkId(@RequestParam("userId") String userId) {
        boolean isAvailable = service.checkId(userId);
        return isAvailable ? "OK" : "EXIST";
    }

    // 닉네임 유효성 검증 (닉네임 중복 확인) - javascript - JQuery AJAX
    // 회원 닉네임중 입력받은 ID와 일치하는 정보가 있으면 (이미 사용중이면) false, 없으면 (사용 가능하면) true
    @Override
    @RequestMapping("/member/checkNickname.do")
    @ResponseBody
    public String checkNickname(@RequestParam("userNickname") String userNickname) {
        boolean isAvailable = service.checkNickname(userNickname);
        return isAvailable ? "OK" : "EXIST";
    }

    // 아이디 찾기
    // 이름과 생년월일을 입력받고 아이디 찾기
    @Override
    @ResponseBody
    @RequestMapping(value = "/findById.do", method = RequestMethod.POST)
    public String findById(@RequestParam("userName") String userName, @RequestParam("userBirth") String userBirth) {
        String userId = service.findById(userName, userBirth);
        if(userId != null) {
            // 아이디 찾기 성공
            return userId;
        } else {
            // 일치하는 사용자가 없음
            return "not found";
        }
    }

    // 비밀번호 변경 part1
    // 비밀번호 찾기 입력창에서 입력한 아이디로 식별자 아이디 찾기
    @Override
    @ResponseBody
    @RequestMapping(value = "/findByPw.do", method = RequestMethod.POST)
    public String findByPw(@RequestParam("userId") String userId, @RequestParam("userBirth") String userBirth) {
        boolean isAvailable = service.findByPw(userId, userBirth);
        return isAvailable ? "OK" : "FAIL";
    }

    // 비밀번호 변경 part2
    // 식별자 아이디로 회원정보를 찾은 후 해당 회원의 비밀번호를 입력받은 비밀번호로 변경
    @Override
    @ResponseBody
    @RequestMapping(value = "/findByAccountID_useId", method = RequestMethod.POST)
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

    // 비밀번호 변경 part3
    // 식별자 아이디로 회원정보를 찾은 후 입력받은 비밀번호로 변경
    @Override
    @RequestMapping(value = "/changePw.do", method = RequestMethod.POST)
    @ResponseBody
    public String changePw(@RequestParam("changePw") String changePw, @RequestParam("accountId") String accountId) {
        boolean isAvailable = service.changePw(changePw, accountId);
        return isAvailable ? "SUCCESS" : "FAIL";
    }

    // 로그아웃
    // 로그아웃시 로그인 화면으로 리다이렉트되며 세션 초기화
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
