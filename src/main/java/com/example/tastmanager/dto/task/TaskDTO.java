package com.example.tastmanager.dto.task;

import com.example.tastmanager.dto.note.NoteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO{
    private Long id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
    private List<NoteDTO> notes;
}
