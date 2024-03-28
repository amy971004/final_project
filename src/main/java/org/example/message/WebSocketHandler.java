package org.example.message;

import org.example.member.dao.MemberDAO;
import org.example.member.dto.MemberDTO;
import org.example.message.dao.MessageDAO;
import org.example.message.dto.MessageDTO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler implements org.springframework.web.socket.WebSocketHandler {

    public MemberDAO memberDAO;
    public MessageDAO messageDAO;

    @Autowired
    public WebSocketHandler(MemberDAO memberDAO, MessageDAO messageDAO) {
        this.memberDAO = memberDAO;
        this.messageDAO = messageDAO;
    }

    // 사용자 ID와 웹소켓 세션을 매핑하는 Map
    private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    // 다수의 채팅방을 처리하기 위한 채팅방별 세션 매핑 Map
    private final Map<String, List<WebSocketSession>> roomSessions = new ConcurrentHashMap<>();


    // 메시지를 전달할 때 사용하는 메서드
    public void sendMessageToRoom(String roomId, String payload) {
        List<WebSocketSession> sessions = roomSessions.get(roomId); // 해당 채팅방의 WebSocket 세션 목록 조회
        if (sessions != null) {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    try {
                        session.sendMessage(new TextMessage(payload)); // 해당 세션에 메시지 전송
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 웹소켓 연결이 성립되면 사용자 ID와 웹소켓 세션을 매핑하여 저장하는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userAccountId = (String) session.getAttributes().get("accountID");
        userSessions.put(userAccountId, session); // 사용자 ID와 웹소켓 세션을 매핑하여 저장
    }

    // 웹소켓 연결이 종료되면 사용자 ID와 매핑된 웹소켓 세션을 제거하는 메서드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String userAccountId = (String) session.getAttributes().get("accountID");
        userSessions.remove(userAccountId); // 사용자 ID와 매핑된 웹소켓 세션을 제거
    }

    // 핸들러에서 오류가 발생했을 때 처리하는 메서드
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        System.err.println("웹소켓 오류 발생: " + exception.getMessage());
    }

    // 메시지 처리 로직을 구현하는 메서드
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 메시지 처리 로직
        // 클라이언트로부터 받은 메시지(JSON 형식)를 String으로 변환
        String payload = message.getPayload().toString();
        JSONObject jsonPayload = new JSONObject(payload);

        // JSON 객체에서 필요한 정보 추출
        String roomId = jsonPayload.getString("roomId");
        String messageText = jsonPayload.getString("message");

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@roomId: " + roomId );
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@roomId: " + messageText );

        // 서블릿 세션에 저장되어있던 accountId(로그인시 저장됨)
        String senderAccountId = (String) session.getAttributes().get("accountID");
        // 유저 정보 가져오기
        MemberDTO memberDTO = memberDAO.findMemberByAccountId(senderAccountId);
        String userId = memberDTO.getUserId();
        String userName = memberDTO.getUserName();

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setRoomId(roomId);
        messageDTO.setSenderId(senderAccountId);
        messageDTO.setSenderUserId(userId);
        messageDTO.setSenderUserName(userName);
        messageDTO.setMessageText(messageText);

        // 메시지 데이터베이스에 저장
        messageDAO.saveMessage(messageDTO);

        List<String> Participants = messageDAO.getParticipantsByRoomId(roomId);

        // 해당 채팅방의 모든 참여자에게 메시지 전송 (브로드캐스트 로직 필요)
        // session.sendMessage(new TextMessage(payload)); // 예시: 에코 보내기
        sendMessageToRoom(roomId, payload); // 예시: 방송 메서드 호출
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
