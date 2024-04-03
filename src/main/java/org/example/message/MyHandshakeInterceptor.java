package org.example.message;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class MyHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        if (request instanceof ServletServerHttpRequest servletRequest) {
            // 쿼리 파라미터에서 roomId 추출
            String roomId = servletRequest.getServletRequest().getParameter("roomId");
            if (roomId != null && !roomId.isEmpty()) {
                // roomId가 존재하는 경우, WebSocket 세션의 속성으로 roomId 추가
                attributes.put("roomId", roomId);
            }

            // HttpSession에서 accountID 가져오기 로직은 그대로 유지
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                String accountID = (String) session.getAttribute("accountID");
                if (accountID != null) {
                    attributes.put("accountID", accountID);
                }
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        // 핸드셰이크 후 처리할 로직 (필요한 경우)
    }
}
