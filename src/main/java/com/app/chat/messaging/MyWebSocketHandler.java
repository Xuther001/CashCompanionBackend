package com.app.chat.messaging;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.app.chat.common.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("WebSocket connection established: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle the received WebSocket message
        String payload = message.getPayload();

        // Parse the incoming JSON payload
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> messageMap = objectMapper.readValue(payload, new TypeReference<Map<String, Object>>() {});

        System.out.println("Received message: " + message.getPayload());
        session.sendMessage(new TextMessage(message.getPayload()));

        System.out.println("this is the token: " + messageMap.get("token"));

        String content = (String) messageMap.get("content");
        String senderUsername = (String) messageMap.get("senderUsername");
        String receiverUsername = (String) messageMap.get("receiverUsername");

        Message receivedMessage = new Message();
        receivedMessage.setContent(content);
        receivedMessage.setSender(senderUsername);
        receivedMessage.setReceiver(receiverUsername);
        messageRepository.save(receivedMessage);

        System.out.println(content +  " from " + senderUsername + " to " + receiverUsername);

    }
}
