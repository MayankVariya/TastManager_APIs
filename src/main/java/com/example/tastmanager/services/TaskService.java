package com.example.tastmanager.services;

import com.example.tastmanager.dto.task.TaskDTO;
import com.example.tastmanager.dto.task.TaskRequestDTO;

import java.util.*;

public interface TaskService {

    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long id);

    TaskDTO createNewTask(TaskRequestDTO taskRequestDTO);

    void deleteTaskById(Long id);

    TaskDTO updateTask(Long id, TaskRequestDTO taskRequestDTO);

    TaskDTO updatePartialTask(Long id, Map<String, Object> updates);
}

