package org.example;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping({"/"})
    public String main1(HttpServletRequest request){return "login";}

    // 권한 검증
    @RequestMapping({"/main"})
    public ModelAndView main2(HttpServletRequest request) {

        // 세션에서 ROLE 정보 가져오기
        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("ROLE");
        String accountID = (String) session.getAttribute("accountID");

        logger.info("################### 메인페이지 요청 ###################");
        logger.info("ROLE: " + role);
        logger.info("accountID: " + accountID);
        // 권한이 있을 경우 (관리자 or 회원)
        if (role != null) {

            // 권한이 관리자일 때
            if ("ADMIN".equals(role)) {
                logger.info("################### 관리자 ###################");
                logger.info("ROLE: " + role);
                logger.info("accountID: " + accountID);
                return new ModelAndView("main");
            }
            // 권한이 회원일 때
            else if ("USER".equals(role)) {
                logger.info("################### 일반 사용자 ###################");
                logger.info("ROLE: " + role);
                logger.info("accountID: " + accountID);
                return new ModelAndView("main");
            }

        }
        // 세션 정보가 없거나, ROLE이 null인 경우는 기본 설정된 리다이렉션 대상("/")으로 이동
        // 로그인 페이지로 리다이렉트하면서, 쿼리 스트링으로 warning=loginRequired를 추가
        return new ModelAndView("redirect:/?warning=loginRequired");
    }


    @RequestMapping({"/test"})
    public String test(){
        return "test";
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
