package org.example.profile.controller;

import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.member.dto.MemberDTO;
import org.example.profile.dto.ProfileDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Member;
import java.util.Map;

public interface ProfileController {
    // 프로필 정보 띄우기
    public ModelAndView profileView(
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception;
    // 프로필 정보 변경 페이지
    public ModelAndView modprofile(
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception;

    // 아이디 중복 확인
    public String checkId(@RequestParam("userId") String userId,
                          HttpServletRequest request
    ) throws Exception;

    // 닉네임 중복 확인
    public String checkNickname (@RequestParam("userNickname") String nickname,
                          HttpServletRequest request
    ) throws Exception;

    // 프로필 정보 수정
    public ModelAndView updateProfile(
            @ModelAttribute("dto") ProfileDTO dto,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception;

    // 이미지, 소개 수정 페이지
    public ModelAndView editImg(
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception;

    // 이미지, 소개 변경
    public ModelAndView upload(
            @RequestParam("content") String content,
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception;

//    public void download(@RequestParam("imageFileName") String imageFileName,
//                         @RequestParam("accountId") String accountId,
//                         HttpServletResponse response) throws Exception;

    @RequestMapping("/profile/download.do")
    void download(String imageFileName,
                  String accountId,
                  HttpServletResponse response,
                  HttpServletRequest request) throws Exception;
}

