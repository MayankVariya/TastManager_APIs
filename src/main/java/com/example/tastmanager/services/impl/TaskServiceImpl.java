package com.example.tastmanager.services.impl;

import com.example.tastmanager.dto.TaskDTO;
import com.example.tastmanager.dto.TaskRequestDTO;
import com.example.tastmanager.entities.Task;
import com.example.tastmanager.repositories.TaskRepository;
import com.example.tastmanager.services.TaskService;
import com.example.tastmanager.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .toList();
    }

    public TaskDTO getTaskById(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + id));

        return modelMapper.map(task, TaskDTO.class);
    }

    public TaskDTO createNewTask(TaskRequestDTO taskRequestDTO) {
        var task = modelMapper.map(taskRequestDTO, Task.class);
        var savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskDTO.class);
    }

    public void deleteTaskById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete. Task not found with ID: " + id);
        }

        taskRepository.deleteById(id);
    }

    public TaskDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + id));

        modelMapper.map(taskRequestDTO, task);

        var updatedEntity = taskRepository.save(task);
        return modelMapper.map(updatedEntity, TaskDTO.class);
    }

    public TaskDTO updatePartialTask(Long id, Map<String, Object> updates) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "title":
                    task.setTitle((String) value);
                    break;
                case "description":
                    task.setDescription((String) value);
                    break;
                case "deadline":
                    task.setDeadline(DateUtils.parseDate((String) value));
                    break;
                case "completed":
                    task.setCompleted((Boolean) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field is not supported.");
            }
        });

        var updatedTask = taskRepository.save(task);
        return modelMapper.map(updatedTask, TaskDTO.class);
    }
}
