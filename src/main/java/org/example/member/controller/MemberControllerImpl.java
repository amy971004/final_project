package org.example.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.member.dto.MemberDTO;
import org.example.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

// 회원 컨트롤러의 구현체
@RestController
public class MemberControllerImpl implements MemberController{
    @Autowired
    private MemberService service;

    // 회원가입 페이지로 이동
    @Override
    @RequestMapping("/member/joinMember.do")
    public ModelAndView joinMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("join");
    }

    // 회원 추가
    @Override
    @RequestMapping("/member/addMember.do")
    public ModelAndView addMember(MemberDTO member, HttpServletRequest request, HttpServletResponse response) throws Exception {

        int result = service.addMember(member);

        return new ModelAndView("login");
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

}
