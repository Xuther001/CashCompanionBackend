package com.app.chat.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

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

//     This has CORS issue
//    @GetMapping( "/{userid}")
//    public List<Note> getNotesByUsername(@PathVariable String userid) {
//        return noteRepository.findByUserid(userid);
//    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }


    // Frontend is not using this endpoint at the moment due to CORS issue
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
