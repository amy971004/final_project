package org.example;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping({"/"})
    public String main1(){
        return "login";
    }

    @RequestMapping({"/main"})
    public String main2(){
        return "main";
    }

    // 테스트 성공 확인용 페이지
    @RequestMapping({"/successTest"})
    public String success(HttpServletRequest request){
        logger.info("Session ID: " + request.getSession().getId());
        // 세션에 저장된 정보 출력
        HttpSession session = request.getSession();
        logger.info("RULE: " + session.getAttribute("RULE"));
        logger.info("accountID: " + session.getAttribute("accountID"));
        return "successTest";
    }

    // 테스트 실패 확인용 페이지
    @RequestMapping({"/failTest"})
    public String fail(){
        return "failTest";
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnNoFavicon() {
    }
}
