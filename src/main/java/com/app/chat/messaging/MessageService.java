package com.app.chat.messaging;

import com.app.chat.common.entity.Message;
import com.app.chat.common.entity.User;
import com.app.chat.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private final MessageRepository messageRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

//    public void saveMessage(MessageRequest messageRequest) {
//        User sender = userRepository.findById(messageRequest.getSenderId()).orElse(null);
//        User receiver = userRepository.findById(messageRequest.getReceiverId()).orElse(null);
//
//        if (sender != null && receiver != null) {
//            Message message = new Message();
//            message.setContent(messageRequest.getContent());
//            message.setSender(sender);
//            message.setReceiver(receiver);
//            messageRepository.save(message);
//        }
//    }
}
