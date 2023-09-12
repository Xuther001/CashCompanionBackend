package com.app.chat.messaging;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageDTO {
    private String senderUsername;
    private String receiverUsername;
    private String content;
}
