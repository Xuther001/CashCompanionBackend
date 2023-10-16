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
//@CrossOrigin(origins = "/api/v1/notes")
//@CrossOrigin(origins = {"/api/v1/notes", "/api/v1/notes/user/*"})
//@CrossOrigin(origins = {"http://localhost:3000"})
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
        Integer noteId = note.getId();
        return ResponseEntity.ok("Note created successfully with ID: " + noteId);
    }

    // Retrieving Notes by Username
    @GetMapping("/user/{userid}")
    @CrossOrigin(origins = "/api/v1/notes/user/{userid}")
    public List<Note> getNotesByUsername(@PathVariable String userid) {
        return noteRepository.findByUserid(userid);
    }

//    @GetMapping
//    public List<Note> getAllNotes() {
//        return noteRepository.findAll();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeNoteById(@PathVariable Integer id) {
        Optional<Note> noteOptional = noteRepository.findById(id);

        if (noteOptional.isPresent()) {
            Note note = noteOptional.get();
            noteRepository.delete(note);
            return ResponseEntity.ok("Note with ID " + id + " has been removed.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
