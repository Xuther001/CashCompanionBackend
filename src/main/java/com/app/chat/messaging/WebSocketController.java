package com.app.chat.messaging;

import com.app.chat.common.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/api/v1")
public class WebSocketController {

    private final MessageService messageService;

    public WebSocketController(MessageService messageService) {
        this.messageService = messageService;
    }

//    @MessageMapping("/send-message")
//    public void handleMessage(@Payload MessageRequest messageRequest) {
//        messageService.saveMessage(messageRequest);
//    }
}
