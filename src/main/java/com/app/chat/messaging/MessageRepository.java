package com.app.chat.messaging;

import com.app.chat.common.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderAndReceiver(String sender, String receiver);

    List<Message> findBySender(String sender);
}
