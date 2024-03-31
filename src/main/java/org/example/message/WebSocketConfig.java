package org.example.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private ChatHandler chatHandler;

    // ChatHandler 빈을 주입받습니다.
    @Autowired
    public WebSocketConfig(ChatHandler chatHandler) {
        this.chatHandler = chatHandler;
    }

//    @Autowired
//    private ChatHandler chatHandler; // 필드 주입 사용


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        // ChatHandler 를 /chat 엔드포인트에 등록합니다.
        registry.addHandler(chatHandler, "/chat").setAllowedOrigins("*")
                .addInterceptors(new MyHandshakeInterceptor()); // HandshakeInterceptor 추가
        // 여기서 chatHandler는 Spring에 의해 자동으로 주입된 ChatHandler 인스턴스입니다.
        // 이 방식으로 필요한 DAO 의존성도 ChatHandler에 제공됩니다.

        // RoomsHandler 를 /rooms 엔드포인트에 등록합니다.
        registry.addHandler(new RoomsHandler(), "/rooms").setAllowedOrigins("*")
                .addInterceptors(new MyHandshakeInterceptor()); // 필요한 경우 RoomsHandler에도 추가

    }

}
