package com.app.chat.notes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteRequest {
    private String content;
    private String userid; // This represents the user_id
}
