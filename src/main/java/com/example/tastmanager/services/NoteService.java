package com.example.tastmanager.services;

import com.example.tastmanager.dto.note.NoteDTO;
import com.example.tastmanager.dto.note.NoteRequestDTO;

import java.util.List;
import java.util.Map;

public interface NoteService {
    List<NoteDTO> getAllNotes(Long taskId);

    NoteDTO createNote(Long taskId, NoteRequestDTO noteRequestDTO);

    NoteDTO getNoteById(Long taskId,Long noteId);

    void deleteNoteById(Long taskId,Long noteId);

    NoteDTO updateNote(Long taskId,Long noteId, Map<String,Object> updates);
}
