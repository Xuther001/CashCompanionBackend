package com.app.chat.messaging;

import com.app.chat.common.security.Jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Autowired
    private  MyWebSocketHandler myWebSocketHandler; // Your custom WebSocket handler

    @Autowired
    public WebSocketConfig(UserDetailsService userDetailsService, JwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("hit");
        registry.addHandler(myWebSocketHandler, "/ws").setAllowedOrigins("*");
    }
}
