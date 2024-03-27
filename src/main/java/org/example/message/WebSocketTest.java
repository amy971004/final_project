package org.example.message;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("websocket-config.xml");

        String url = "ws://localhost:8081/ws";
        WebSocketConnectionManager manager = new WebSocketConnectionManager(
                new StandardWebSocketClient(),
                new TextWebSocketHandler(),
                url);
        manager.start();

    }
}

