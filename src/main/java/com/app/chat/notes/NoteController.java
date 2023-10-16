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
        Long userId = noteRequest.getUserId();  // Extract user_id from the request
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            // Handle the case where the user doesn't exist
            return ResponseEntity.badRequest().body("Invalid user ID");
        }

        Note note = new Note();
        note.setContent(noteRequest.getContent());
        note.setUser(user);

        noteRepository.save(note);
        return ResponseEntity.ok("Note created successfully");
    }

    // Retrieving Notes by Username
    @GetMapping("/user/{username}")
    public List<Note> getNotesByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            return noteRepository.findAllByUser(user);
        } else {
            // Handle the case where the user does not exist
            return new ArrayList<>();
        }
    }
}
