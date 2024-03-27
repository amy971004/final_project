package org.example.message;

import org.springframework.web.socket.*;

public class WebSocketHandler implements org.springframework.web.socket.WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("웹소켓 연결 성공: " + session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 메시지 처리 로직
        // 클라이언트로부터 받은 메시지를 String으로 변환
        String msg = message.getPayload().toString();
        System.out.println("Received message: " + msg);

        // 클라이언트에게 메시지 에코 보내기
        session.sendMessage(new TextMessage(msg));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        System.err.println("웹소켓 오류 발생: " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("웹소켓 연결 종료: " + session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
