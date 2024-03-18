package org.example.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.member.dto.MemberDTO;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // 회원 추가
    public ModelAndView addMember(
            @ModelAttribute("dto") MemberDTO member,
            HttpServletRequest request,
            HttpServletResponse response
            )throws Exception;

    // 아이디 중복 확인
    public String checkId(@RequestParam("userId") String userId);

    // 닉네임 중복 확인
    String checkNickname(@RequestParam("userNickname") String userNickname);

}
