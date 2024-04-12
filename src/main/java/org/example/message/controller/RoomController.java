package org.example.message.controller;

import jakarta.servlet.http.HttpSession;
import org.example.message.dto.RoomDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface RoomController {
    @RequestMapping("/roomTest")
    ModelAndView websocketTestPage();

    @RequestMapping("/findRoom.do")
    String findRoom(@RequestParam("receiverId") String receiverId, HttpSession session);

    // 채팅방 전체 목록 보기
    @RequestMapping("/chatRooms")
    ModelAndView showChatRooms(HttpSession session, Model model);

    // roomId 로 Room 정보 가져오기
    @GetMapping("/main/chatRooms/getRoomInfo.do")
    @ResponseBody
    RoomDTO getRoomByRoomId(@RequestParam("roomId") String roomId, HttpSession session);

    // roomId 로 해당 Room 삭제하기
    @GetMapping("/main/chatRooms/deleteRoom.do")
    @ResponseBody
    boolean deleteRoom(@RequestParam("roomId") String roomId);
}
