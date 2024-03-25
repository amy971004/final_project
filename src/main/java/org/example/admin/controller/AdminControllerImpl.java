package org.example.admin.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

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
    public ModelAndView userManagement(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("userManagement");

        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("ROLE");

        if (!Objects.equals(role, "ADMIN")){

        }

        String realPath = request.getServletContext().getRealPath("/");
        mav.addObject("realPath", realPath); // "realPath"라는 이름으로 realPath 변수를 뷰에 전달
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/main/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam("userId") String userId) {
            boolean result = service.deleteUser(userId);
            return result ? "SUCCESS" : "FAIL";
    }


}
