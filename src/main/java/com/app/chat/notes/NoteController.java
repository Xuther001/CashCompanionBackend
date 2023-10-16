package com.app.chat.notes;

import com.app.chat.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.chat.common.entity.User;
import com.app.chat.common.security.service.UserService;
import com.app.chat.module.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notes")
@CrossOrigin(origins = "/api/v1/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public NoteController() {
    }

    @PostMapping
    public ResponseEntity<?> addNote(@RequestBody NoteRequest noteRequest) {
        if (noteRequest.getContent() == null || noteRequest.getContent().isEmpty()) {
            // Handle the case where content is missing or empty
            return ResponseEntity.badRequest().body("Note content is missing.");
        }

        Note note = new Note();
        note.setContent(noteRequest.getContent());
        note.setUserid(noteRequest.getUserid());
        // Set the user (userId) for the note here, depending on your logic
        // note.setUser(user);

        noteRepository.save(note);
        return ResponseEntity.ok("Note created successfully");
    }

    // Retrieving Notes by Username
    @GetMapping("/user/{username}")
    public List<Note> getNotesByUsername(@PathVariable String username) {
        return noteRepository.findAllByUserid(username);
    }
}
