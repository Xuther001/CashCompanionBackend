package com.app.chat.messaging;

import com.app.chat.common.entity.Message;
import com.app.chat.common.entity.User;
import com.app.chat.module.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chat")
public class MessageController {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public MessageController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages(
            @RequestParam String senderUsername,
            @RequestParam String receiverUsername) {

        List<Message> messages = messageRepository.findBySenderAndReceiver(senderUsername, receiverUsername);

        return ResponseEntity.ok(messages);
    }
}