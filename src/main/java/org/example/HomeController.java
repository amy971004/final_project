package org.example;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "/main"})
    public String main1(){
        return "login";
    }

    @RequestMapping({"/successTest"})
    public String success(HttpServletRequest request){
        logger.info("Session ID: " + request.getSession().getId());
        return "successTest";
    }

    @RequestMapping({"/failTest"})
    public String fail(){
        return "failTest";
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnNoFavicon() {
    }
}
