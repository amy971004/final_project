package org.example.message.controller;

import lombok.extern.log4j.Log4j;
import org.example.member.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j
public class ChatController {

    @GetMapping("/chat")
    public void chat(MemberDTO memberDTO) {

        log.info("==================================");
        log.info("@ChatController, GET Chat / Username : " + memberDTO.getUserName());

    }
}
