package org.example.kakao.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface KakaoController {
    public ModelAndView kakaoLogin(@RequestParam(value="code", required = false) String code)
        throws Throwable;

}
