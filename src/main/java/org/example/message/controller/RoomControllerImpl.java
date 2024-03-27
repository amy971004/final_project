package org.example.message.controller;

import jakarta.servlet.http.HttpSession;
import org.example.message.dao.RoomDAO;
import org.example.message.dto.RoomDTO;
import org.example.message.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class RoomControllerImpl implements RoomController{

    private final RoomService service;
    private final RoomDAO roomDAO;

    @Autowired
    public RoomControllerImpl(RoomService service, RoomDAO roomDAO) {
        this.service = service;
        this.roomDAO = roomDAO;

    }

    @RequestMapping("/roomTest")
    public ModelAndView websocketTestPage() {
        return new ModelAndView("roomTest");
    }

    @RequestMapping("/findRoom.do")
    public ModelAndView findRoom(@RequestParam("receiverId") String receiverId, HttpSession session) {
        // 세션에서 발신자의 accountId 가져오기
        String senderAccountId = (String) session.getAttribute("accountID");
        // 수신자의 accountId 가져오기
        String receiverAccountId = service.findReceiverAccountId(receiverId);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" +
                "보내는 사람 : " + senderAccountId + "받는 사람 : " + receiverAccountId +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        // 발신자와 수신자로 채팅방 확인 및 생성
        RoomDTO roomDTO = service.findOrCreateChatRoom(senderAccountId, receiverAccountId);

        if (roomDTO != null){
            // 채팅 창여자 목록 테이플에 추가
            roomDAO.addParticipant(roomDTO.getRoomId(), senderAccountId);
            roomDAO.addParticipant(roomDTO.getRoomId(), receiverAccountId);
        }

        // 채팅방으로 이동
//        ModelAndView modelAndView = new ModelAndView("/OK");
//        modelAndView.addObject("chatRoomId", roomDTO.getRoomId());
        return new ModelAndView("OK");
    }

    // 채팅방 전체 목록 보기
    @RequestMapping("/chatRooms")
    public ModelAndView showChatRooms(HttpSession session, Model model) {
        // 세션에서 발신자의 accountId 가져오기
        String senderAccountId = (String) session.getAttribute("accountID");

        // 발신자가 참여하고 있는 모든 채팅방을 조회
        List<RoomDTO> rooms = service.findAllRoomsByAccountId(senderAccountId);

        // 조회한 채팅방 목록을 모델에 추가
        model.addAttribute("rooms", rooms);

        return new ModelAndView("chatRooms");
    }
}
