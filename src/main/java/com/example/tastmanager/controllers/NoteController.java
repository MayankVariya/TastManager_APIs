package com.example.tastmanager.controllers;

import com.example.tastmanager.dto.note.NoteDTO;
import com.example.tastmanager.dto.note.NoteRequestDTO;
import com.example.tastmanager.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks/{task_id}/notes")
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAllNotes(@PathVariable("task_id") Long taskId) {
        return ResponseEntity.ok(noteService.getAllNotes(taskId));
    }

    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@PathVariable("task_id") Long taskId, @RequestBody NoteRequestDTO noteRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(taskId, noteRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable("task_id") Long taskId, @PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(taskId, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable("task_id") Long taskId, @PathVariable Long id) {
        noteService.deleteNoteById(taskId, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NoteDTO> updateNote(@PathVariable("task_id") Long taskId, @PathVariable Long id, Map<String, Object> updates) {
        return ResponseEntity.ok(noteService.updateNote(taskId, id, updates));
    }
}
