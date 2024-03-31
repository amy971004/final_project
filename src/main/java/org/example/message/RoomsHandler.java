package org.example.message;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class RoomsHandler implements org.springframework.web.socket.WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 클라이언트가 웹소켓에 연결되면 호출됩니다.
        // 웹소켓 세션을 파라미터로 받아 해당 세션과 연결된 클라이언트와의 작업을 처리할 수 있습니다.
        // 일반적으로 클라이언트와의 연결이 확립되면 수행할 초기화 작업을 수행합니다.
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 클라이언트로부터 메시지를 수신했을 때 호출됩니다.
        // 웹소켓 세션과 수신된 메시지를 파라미터로 받아 해당 메시지를 처리하는 작업을 수행합니다.
        // 이 메서드에서는 클라이언트가 보낸 메시지에 대한 처리 로직을 구현할 수 있습니다.
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 전송 오류가 발생했을 때 호출됩니다.
        // 웹소켓 세션과 발생한 예외를 파라미터로 받아 오류 처리 작업을 수행합니다.
        // 이 메서드에서는 예외에 따른 오류 처리 로직을 구현할 수 있습니다.
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 클라이언트와의 연결이 닫혔을 때 호출됩니다.
        // 웹소켓 세션과 연결 종료 상태를 파라미터로 받아 해당 세션과의 작업을 마무리합니다.
        // 일반적으로 클라이언트와의 연결이 닫힌 후의 후속 작업을 수행합니다.
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
        // 부분 메시지를 지원하는지 여부를 반환합니다.
        // WebSocket 메시지가 일부만 전송되었을 때 처리할 수 있는지 여부를 설정합니다.
        // 여기서는 false를 반환하여 부분 메시지를 지원하지 않음을 나타냅니다.
    }
}
