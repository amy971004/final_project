package org.example.message.controller;

import jakarta.servlet.http.HttpSession;
import org.example.member.dao.MemberDAO;
import org.example.member.dto.MemberDTO;
import org.example.message.dao.RoomDAO;
import org.example.message.dto.RoomDTO;
import org.example.message.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    @ResponseBody
    @RequestMapping("/findRoom.do")
    public String findRoom(@RequestParam("receiverId") String receiverId, HttpSession session) {
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
            int result1 = roomDAO.findParticipantsByRoomId(roomDTO.getRoomId());
            if (result1 == 0) {
                roomDAO.addParticipant(roomDTO.getRoomId(), senderAccountId, sendUser.getUserId(), sendUser.getUserName());
                roomDAO.addParticipant(roomDTO.getRoomId(), receiverAccountId, receiverUer.getUserId(), receiverUer.getUserName());
            }
            return "SUCCESS";
        }

        // 채팅방으로 이동
        return "SUCCESS";
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
        List<String> receiveUserAccountId = service.findReceiver(senderAccountId);

        // 수신자의 정보 리스트 조회
        List<MemberDTO> receiveUsers = new ArrayList<>();
        for (String s : receiveUserAccountId) {
            MemberDTO receiveUser = memDAO.findMemberByAccountId(s);
            receiveUsers.add(receiveUser);
        }

        // 조회한 채팅방 목록을 모델에 추가
        model.addAttribute("rooms", rooms);
        model.addAttribute("user", user);
        model.addAttribute("receiveUsers", receiveUsers);

        return new ModelAndView("chatRooms");
    }

    // roomId 로 Room 정보 가져오기
//    @Override
//    @GetMapping("/main/chatRooms/getRoomInfo.do")
//    @ResponseBody
//    public RoomDTO getRoomByRoomId(@RequestParam("roomId") String roomId, HttpSession session) {
//        RoomDTO room = roomDAO.getRoomByRoomId(roomId);
//        String myAccountId = (String) session.getAttribute("accountID");
//        String opponentName = roomDAO.findOpponentName(roomId, myAccountId);
//        room.setOpponentName(opponentName);
//        return room;
//    }

    @Override
    @GetMapping("/main/chatRooms/getRoomInfo.do")
    @ResponseBody
    public RoomDTO getRoomByRoomId(@RequestParam("roomId") String roomId, HttpSession session) {
        return roomDAO.getRoomByRoomId(roomId);
    }

    // roomId 로 해당 Room 삭제하기
    @Override
    @ResponseBody
    @DeleteMapping("/main/chatRooms/deleteRoom.do")
    public boolean deleteRoom(@RequestParam("roomId") String roomId) {
        return roomDAO.deleteRoom(roomId);
    }

    @GetMapping("/main/chatRooms/selectReceiver.do")
    @ResponseBody
    public MemberDTO selectReceiver(@RequestParam("accountId") String accountId){
        return roomDAO.selectReceiver(accountId);
    }

}
