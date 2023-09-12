package com.app.chat.messaging;


import lombok.Getter;
import lombok.Setter;

//this is DTO
@Getter
@Setter
public class MessageRequest {

    @Getter
    private String content;
    private String senderUsername;
    private String receiverUsername;

    public MessageRequest(String content, String senderUsername, String receiverUsername) {
        this.content = content;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
    }

}
