package org.example.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 관리자 컨트로러 인터페이스
public interface AdminController {

    // 회원관리 페이지 이동
    @RequestMapping("/main/userManagement.do")
    ModelAndView userManagement();
}
