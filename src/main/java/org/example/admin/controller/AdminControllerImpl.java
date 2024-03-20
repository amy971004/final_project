package org.example.admin.controller;

import org.example.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

// 관리자 컨트롤러의 구현체
@RestController
public class AdminControllerImpl implements AdminController {

    private final AdminService service;

    @Autowired
    public AdminControllerImpl(AdminService service) {
        this.service = service;
    }

    // 회원관리 페이지 이동
    @Override
    @RequestMapping("/main/userManagement.do")
    public ModelAndView userManagement() {
        return new ModelAndView("userManagement");
    }

}
