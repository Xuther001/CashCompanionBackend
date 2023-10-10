package com.app.chat.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notes")
@CrossOrigin(origins = "/api/v1/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @PostMapping
    public Note addNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }
}