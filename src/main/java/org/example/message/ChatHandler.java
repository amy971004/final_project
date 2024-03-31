package org.example.message;

import org.example.member.dao.MemberDAO;
import org.example.message.dao.MessageDAO;
import org.example.message.dto.MessageDTO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatHandler implements WebSocketHandler{

    public MemberDAO memberDAO;
    public MessageDAO messageDAO;

    // 채팅방 ID와 해당 채팅방의 사용자 세션 목록을 매핑하는 Map
    private final Map<String, List<WebSocketSession>> roomSessions = new ConcurrentHashMap<>();

    @Autowired
    public ChatHandler(MemberDAO memberDAO, MessageDAO messageDAO) {
        this.memberDAO = memberDAO;
        this.messageDAO = messageDAO;
    }

    // 사용자 ID와 웹소켓 세션을 매핑하는 Map
    private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 클라이언트가 웹소켓에 연결되면 호출됩니다.
        // 웹소켓 세션을 파라미터로 받아 해당 세션과 연결된 클라이언트와의 작업을 처리할 수 있습니다.
        // 일반적으로 클라이언트와의 연결이 확립되면 수행할 초기화 작업을 수행합니다.

        String roomId = (String) session.getAttributes().get("roomId");
        if (roomId != null) {
            roomSessions.computeIfAbsent(roomId, k -> new ArrayList<>()).add(session);
        } else {
            // roomId가 null일 경우의 처리 로직
            System.err.println("Room ID is null for session: " + session.getId());
            // 세션을 종료하거나, 오류 메시지를 보내는 등의 처리를 할 수 있습니다.
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 클라이언트로부터 메시지를 수신했을 때 호출됩니다.
        // 웹소켓 세션과 수신된 메시지를 파라미터로 받아 해당 메시지를 처리하는 작업을 수행합니다.
        // 이 메서드에서는 클라이언트가 보낸 메시지에 대한 처리 로직을 구현할 수 있습니다.

        // 메시지 처리 로직
        // 클라이언트로부터 받은 메시지(JSON 형식)를 String 으로 변환
        String payload = message.getPayload().toString();
        JSONObject jsonMessage = new JSONObject(payload);

        // JSON 에서 메시지 정보 추출
        String roomId = jsonMessage.getString("roomId");
        String senderUserId = jsonMessage.getString("senderUserId");
        String senderUserName = jsonMessage.getString("senderUserName");
        String messageText = jsonMessage.getString("messageText");
        String sentAt = jsonMessage.getString("sentAt");

        // 서블릿 세션에 저장되어있던 accountId(로그인시 저장됨)
        String senderAccountId = (String) session.getAttributes().get("accountID");

        System.out.println("####@@1234 어카운트아이디 가져오기 테스트 : " + senderAccountId);

        // 데이터베이스에 메세지 저장 part-1
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setRoomId(roomId);
        messageDTO.setSenderId(senderAccountId);
        messageDTO.setSenderUserId(senderUserId);
        messageDTO.setSenderUserName(senderUserName);
        messageDTO.setMessageText(messageText);

        // 메시지 데이터베이스에 저장 part-2
        messageDAO.saveMessage(messageDTO);

        // 채팅방의 모든 사용자에게 메시지 브로드캐스트
        List<WebSocketSession> sessions = roomSessions.getOrDefault(roomId, new ArrayList<>());
        for (WebSocketSession targetSession : sessions) {
            if (targetSession.isOpen()) {
                targetSession.sendMessage(new TextMessage(payload));
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 전송 오류가 발생했을 때 호출됩니다.
        // 웹소켓 세션과 발생한 예외를 파라미터로 받아 오류 처리 작업을 수행합니다.
        // 이 메서드에서는 예외에 따른 오류 처리 로직을 구현할 수 있습니다.

        if (session.isOpen()) {
            session.close();
        }
        System.err.println("웹소켓 오류 발생: " + exception.getMessage());

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 클라이언트와의 연결이 닫혔을 때 호출됩니다.
        // 웹소켓 세션과 연결 종료 상태를 파라미터로 받아 해당 세션과의 작업을 마무리합니다.
        // 일반적으로 클라이언트와의 연결이 닫힌 후의 후속 작업을 수행합니다.

        String roomId = (String) session.getAttributes().get("roomId");
        List<WebSocketSession> sessions = roomSessions.get(roomId);
        if (sessions != null) {
            sessions.remove(session);
            if (sessions.isEmpty()) {
                roomSessions.remove(roomId);
            }
        }

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
        // 부분 메시지를 지원하는지 여부를 반환합니다.
        // WebSocket 메시지가 일부만 전송되었을 때 처리할 수 있는지 여부를 설정합니다.
        // 여기서는 false를 반환하여 부분 메시지를 지원하지 않음을 나타냅니다.
    }

}
