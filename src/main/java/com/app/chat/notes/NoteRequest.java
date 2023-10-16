package com.app.chat.notes;

import lombok.Getter;

@Getter
public class NoteRequest {
    private String content;
    private Long userId; // This represents the user_id

    // Constructors, getters, and setters

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
