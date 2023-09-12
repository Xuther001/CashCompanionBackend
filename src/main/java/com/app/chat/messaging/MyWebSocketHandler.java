package com.app.chat.messaging;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.app.chat.common.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.app.chat.common.security.Jwt.JwtService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MessageRepository messageRepository; // Assuming you have a MessageRepository interface

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
        session.sendMessage(new TextMessage("Echo: " + message.getPayload()));

        System.out.println("this is the token: " + messageMap.get("token"));

        // UserDetails userDetails = this.userDetailsService.loadUserByUsername(messageMap.get("senderUsername").toString());

        // String jwtToken = (String) messageMap.get("token");

        String content = (String) messageMap.get("content");
        String senderUsername = (String) messageMap.get("senderUsername");
        String receiverUsername = (String) messageMap.get("receiverUsername");
        // Perform your WebSocket handling for authorized users here
        // Send the message data to the REST endpoint
//        sendWebSocketDataToRestEndpoint(content, senderUsername, receiverUsername);
        Message receivedMessage = new Message();
        receivedMessage.setContent(payload);

        messageRepository.save(receivedMessage);

        System.out.println(content + senderUsername + receiverUsername);

    }

    private void sendWebSocketDataToRestEndpoint(String content, String senderUsername, String receiverUsername) {
        // Create an instance of RestTemplate and construct the request
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/v1/chat/send"; // Replace with your server URL
        MessageRequest request = new MessageRequest(content, senderUsername, receiverUsername);

        // Send the POST request to the REST endpoint
//        restTemplate.postForObject(url, request, Void.class);
        MessageRequest response = restTemplate.postForObject(url, request, MessageRequest.class);
        System.out.println("HITTTTT:::: Response content: " + response.getContent());
    }
}
