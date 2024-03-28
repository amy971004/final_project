package org.example.message.controller;

import jakarta.servlet.http.HttpSession;
import org.example.member.dao.MemberDAO;
import org.example.member.dto.MemberDTO;
import org.example.member.service.MemberService;
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
    private final MemberDAO memDAO;
    private final RoomDAO roomDAO;
    private final MemberDAO memberDAO;

    @Autowired
    public RoomControllerImpl(RoomService service, RoomDAO roomDAO, MemberDAO memberDAO, MemberDAO memDAO) {
        this.service = service;
        this.roomDAO = roomDAO;
        this.memberDAO = memberDAO;
        this.memDAO = memDAO;
    }

    @Override
    @RequestMapping("/main/roomTest.do")
    public ModelAndView websocketTestPage() {
        return new ModelAndView("roomTest");
    }

    @Override
    @RequestMapping("/findRoom.do")
    public ModelAndView findRoom(@RequestParam("receiverId") String receiverId, HttpSession session) {
        // 세션에서 발신자의 accountId 가져오기
        String senderAccountId = (String) session.getAttribute("accountID");
        // 수신자의 accountId 가져오기
        String receiverAccountId = service.findReceiverAccountId(receiverId);

        // accountId로 회원정보를 담은 DTO 객체 저장
        MemberDTO sendUser = memberDAO.findMemberByAccountId(senderAccountId);
        MemberDTO receiverUer = memberDAO.findMemberByAccountId(receiverAccountId);

        // 발신자와 수신자로 채팅방 확인 및 생성
        RoomDTO roomDTO = service.findOrCreateChatRoom(senderAccountId, receiverAccountId, sendUser.getUserName(), receiverUer.getUserName());

        if (roomDTO != null){
            // 채팅 창여자 목록 테이플에 추가
            roomDAO.addParticipant(roomDTO.getRoomId(), senderAccountId, sendUser.getUserId(), sendUser.getUserName());
            roomDAO.addParticipant(roomDTO.getRoomId(), receiverAccountId, receiverUer.getUserId(), receiverUer.getUserName());
        }

        // 채팅방으로 이동
        // ModelAndView modelAndView = new ModelAndView("/OK");
        // modelAndView.addObject("chatRoomId", roomDTO.getRoomId());
        return new ModelAndView("OK");
    }

    // 채팅방 전체 목록 보기
    @Override
    @RequestMapping("/main/chatRooms")
    public ModelAndView showChatRooms(HttpSession session, Model model) {
        // 세션에서 발신자의 accountId 가져오기
        String senderAccountId = (String) session.getAttribute("accountID");

        // 발신자가 참여하고 있는 모든 채팅방을 조회
        List<RoomDTO> rooms = service.findAllRoomsByAccountId(senderAccountId);
        MemberDTO user = memDAO.findMemberByAccountId(senderAccountId);

        // 조회한 채팅방 목록을 모델에 추가
        model.addAttribute("rooms", rooms);
        model.addAttribute("user", user);

        return new ModelAndView("chatRooms");
}
    }
