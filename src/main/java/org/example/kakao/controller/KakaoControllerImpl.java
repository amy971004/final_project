package org.example.kakao.controller;

import org.example.kakao.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;

@Controller
public class KakaoControllerImpl implements KakaoController{

    @Autowired
    KakaoService service;

    @Override
    // 카카오톡 로그인 후 처리 매핑
    @RequestMapping(value ="/app/login/kakao", method = RequestMethod.GET)
    public ModelAndView kakaoLogin(String code) throws Throwable {
        // 토큰 받아오기
        String token = service.getAccessToken(code);

        // 카카오톡 파라미터에서 유저 정보 가져오기
        HashMap<String ,Object> userInfo = service.getUserInfo(token);
        System.out.println("###nickname#### : " + userInfo.get("nickname"));

        String nickname = userInfo.get("nickname").toString();
        String imageUrl = userInfo.get("image").toString();


        ModelAndView mav = new ModelAndView("join");
        mav.addObject("nickname", nickname);
        mav.addObject("image", imageUrl);
        return mav;
    }


}
