package org.example.message.controller;

import org.example.message.dto.MessageDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface MessageController {
    // roomId 로 모든 message 가져오기
    @GetMapping("/main/chatRooms/getMessage.do")
    @ResponseBody
    List<MessageDTO> getMessagesByRoomId(@RequestParam("roomId") String roomId);
}
