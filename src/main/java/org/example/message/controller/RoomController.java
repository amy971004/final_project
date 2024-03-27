package org.example.message.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface RoomController {
    @RequestMapping("/roomTest")
    ModelAndView websocketTestPage();

    @RequestMapping("/findRoom.do")
    ModelAndView findRoom(@RequestParam("receiverId") String receiverId, HttpSession session);

    // 채팅방 전체 목록 보기
    @RequestMapping("/chatRooms")
    ModelAndView showChatRooms(HttpSession session, Model model);
}
