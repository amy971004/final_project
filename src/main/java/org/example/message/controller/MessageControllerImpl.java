package org.example.message.controller;

import org.example.message.dao.MessageDAO;
import org.example.message.dto.MessageDTO;
import org.example.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageControllerImpl implements MessageController{

    private final MessageService service;
    private final MessageDAO messageDAO;

    @Autowired
    public MessageControllerImpl(MessageService service, MessageDAO messageDAO) {
        this.service = service;
        this.messageDAO = messageDAO;
    }

    // roomId 로 모든 message 가져오기
    @Override
    @GetMapping("/main/chatRooms/getMessage.do")
    @ResponseBody
    public List<MessageDTO> getMessagesByRoomId(@RequestParam("roomId") String roomId) {
        // roomId에 해당하는 메시지 목록을 조회하여 반환
        return messageDAO.getMessagesByRoomId(roomId);
    }


}
