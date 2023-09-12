package com.app.chat.messaging;

import com.app.chat.common.entity.Message;
import com.app.chat.common.entity.User;
import com.app.chat.module.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public ChatController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO) {
        // Convert messageDTO to Message entity
        Message message = new Message();
        message.setContent(messageDTO.getContent());

        // Fetch User entities using sender and receiver usernames (assuming you have a userRepository)
        Optional<User> senderOptional = userRepository.findByUsername(messageDTO.getSenderUsername());
        Optional<User> receiverOptional = userRepository.findByUsername(messageDTO.getReceiverUsername());

        if (senderOptional.isEmpty() || receiverOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Sender or receiver not found");
        }

        User sender = senderOptional.get();
        User receiver = receiverOptional.get();

        message.setSender(sender);
        message.setReceiver(receiver);

        // Save the message using messageRepository
        messageRepository.save(message);

        return ResponseEntity.ok("Message sent successfully");
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages(
            @RequestParam String senderUsername,
            @RequestParam String receiverUsername) {

        // Find sender and receiver users from userRepository
        Optional<User> optionalSender = userRepository.findByUsername(senderUsername);
        Optional<User> optionalReceiver = userRepository.findByUsername(receiverUsername);

        if (optionalSender.isEmpty() || optionalReceiver.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        User sender = optionalSender.get();
        User receiver = optionalReceiver.get();

        // Fetch messages between sender and receiver from messageRepository
        List<Message> messages = messageRepository.findBySenderAndReceiver(sender, receiver);

        return ResponseEntity.ok(messages);
    }
}
