package com.example.tastmanager.services.impl;

import com.example.tastmanager.dto.note.NoteDTO;
import com.example.tastmanager.dto.note.NoteRequestDTO;
import com.example.tastmanager.entities.Note;
import com.example.tastmanager.repositories.NoteRepository;
import com.example.tastmanager.repositories.TaskRepository;
import com.example.tastmanager.services.NoteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<NoteDTO> getAllNotes(Long taskId) {
        var task = taskRepository.findById(taskId).orElseThrow();
        return task.getNotes().stream().map(
                note -> modelMapper.map(note, NoteDTO.class)
        ).toList();
    }

    @Override
    @Transactional
    public NoteDTO createNote(Long taskId, NoteRequestDTO noteRequestDTO) {
        var task = taskRepository.findById(taskId).orElseThrow();

        var note = modelMapper.map(noteRequestDTO, Note.class);

        note.setTask(task);
        noteRepository.save(note);

        return modelMapper.map(note, NoteDTO.class);
    }

    @Override
    public NoteDTO getNoteById(Long taskId, Long noteId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        var note = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if (!note.getTask().getId().equals(task.getId())) {
            throw new IllegalArgumentException("Note does not belong to the specified task");
        }

        return modelMapper.map(note, NoteDTO.class);
    }


    @Override
    @Transactional
    public void deleteNoteById(Long taskId, Long noteId) {
        var note = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if (!note.getTask().getId().equals(taskId)) {
            throw new IllegalArgumentException("Note does not belong to the specified task");
        }

        noteRepository.delete(note);
    }


    @Override
    @Transactional
    public NoteDTO updateNote(Long taskId,Long noteId, Map<String, Object> updates) {
        var note = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if (!note.getTask().getId().equals(taskId)) {
            throw new IllegalArgumentException("Note does not belong to the specified task");
        }

        updates.forEach((key,value)->{
            if (key.equals("content")) {
                note.setContent((String) value);
            }
            throw new IllegalArgumentException("Field is not supported.");
        });

        return modelMapper.map(noteRepository.save(note), NoteDTO.class);
    }
}