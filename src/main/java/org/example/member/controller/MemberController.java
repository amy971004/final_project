package org.example.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.member.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

// 회원 관리 인터페이스
public interface MemberController {
    // 로그인
    @PostMapping("/login.do")
    ModelAndView login(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpServletRequest request);

    // 회원가입 페이지로 이동
    public ModelAndView joinMember(
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception;

//    // 회원 추가
//    public ModelAndView addMember(
//            @ModelAttribute("dto") MemberDTO member,
//            HttpServletRequest request,
//            HttpServletResponse response
//            )throws Exception;

    // 아이디 중복 확인
    public String checkId(@RequestParam("userId") String userId);

    // 닉네임 중복 확인
    String checkNickname(@RequestParam("userNickname") String userNickname);

    // 아이디 찾기
    @RequestMapping(value = "/findById.do", method = RequestMethod.POST)
    @ResponseBody
    String findById(@RequestParam("userName") String userName, @RequestParam("userBirth") String userBirth, HttpServletRequest request);

    // 비밀번호 찾기
    @RequestMapping(value = "/findByPw.do", method = RequestMethod.POST)
    @ResponseBody
    String findByPw(@RequestParam("userId") String userId, @RequestParam("userBirth") String userBirth, HttpServletRequest request);

    // 비밀번호 변경 - 입력한 아이디로 식별자 아이디 찾기
    @RequestMapping(value = "/findByAccountID_useId", method = RequestMethod.POST)
    @ResponseBody
    String findByAccountID_useId(@RequestParam("userId") String userId);

    // 비밀번호 변경
    @RequestMapping(value = "/changePw.do", method = RequestMethod.POST)
    @ResponseBody
    String changePw(@RequestParam("changePw") String changePw, @RequestParam("accountId") String accountId, HttpServletRequest request);

    // 로그아웃
    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    ModelAndView logout(HttpServletRequest request);
}
