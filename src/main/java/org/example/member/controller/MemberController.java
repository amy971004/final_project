package org.example.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.member.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

// 회원 컨트롤러 인터페이스
public interface MemberController {

    // 로그인
    // 아이디 비밀번호를 입력받아 로그인
    @PostMapping("/login.do")
    ModelAndView login(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpServletRequest request);

    // 회원가입 페이지로 이동
    @RequestMapping("/member/joinMember.do")
    ModelAndView joinMember();

    // 회원가입
    // 가입정보를 입력받은 후 회원가입
    @PostMapping("/member/addMember.do")
    ModelAndView addMember(@RequestParam(name = "kakaoImg", required = false) String img, @RequestParam("file") MultipartFile file, @RequestParam("userId") String userId, MemberDTO member, HttpServletRequest request) throws Exception;

    // 회원가입 유효성 검증 (아이디 중복 확인) - javascript - JQuery AJAX
    // 회원 ID중 입력받은 ID와 일치하는 정보가 있으면 (이미 사용중이면) false, 없으면 (사용 가능하면) true
    public String checkId(@RequestParam("userId") String userId);

    // 닉네임 유효성 검증 (닉네임 중복 확인) - javascript - JQuery AJAX
    // 회원 닉네임중 입력받은 ID와 일치하는 정보가 있으면 (이미 사용중이면) false, 없으면 (사용 가능하면) true
    String checkNickname(@RequestParam("userNickname") String userNickname);

    // 아이디 찾기
    // 이름과 생년월일을 입력받고 아이디 찾기
    @ResponseBody
    @RequestMapping(value = "/findById.do", method = RequestMethod.POST)
    String findById(@RequestParam("userName") String userName, @RequestParam("userBirth") String userBirth);

    // 비밀번호 찾기
    // 아이디와 생년월일을 입력받고 비밀번호 찾기
    @ResponseBody
    @RequestMapping(value = "/findByPw.do", method = RequestMethod.POST)
    String findByPw(@RequestParam("userId") String userId, @RequestParam("userBirth") String userBirth);

    // 비밀번호 변경 part1
    // 비밀번호 찾기 입력창에서 입력한 아이디로 식별자 아이디 찾기
    @RequestMapping(value = "/findByAccountID_useId", method = RequestMethod.POST)
    @ResponseBody
    String findByAccountID_useId(@RequestParam("userId") String userId);

    // 비밀번호 변경 part2
    // 식별자 아이디로 회원정보를 찾은 후 해당 회원의 비밀번호를 입력받은 비밀번호로 변경
    @RequestMapping(value = "/changePw.do", method = RequestMethod.POST)
    @ResponseBody
    String changePw(@RequestParam("changePw") String changePw, @RequestParam("accountId") String accountId);

    // 로그아웃
    // 로그아웃시 로그인 화면으로 리다이렉트되며 세션 초기화
    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    ModelAndView logout(HttpServletRequest request);

    // 인증메일 전송
    @ResponseBody
    @RequestMapping("/emailSend.do")
    String sendMail(String userEmail, String userName) throws Exception;
}
