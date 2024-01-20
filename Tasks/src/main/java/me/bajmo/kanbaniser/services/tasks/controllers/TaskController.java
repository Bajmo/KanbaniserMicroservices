package me.bajmo.kanbaniser.services.tasks.controllers;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.bajmo.kanbaniser.services.tasks.converters.TaskConverter;
import me.bajmo.kanbaniser.services.tasks.entities.Task;
import me.bajmo.kanbaniser.services.tasks.exceptions.BoardNotFoundException;
import me.bajmo.kanbaniser.services.tasks.exceptions.TaskNotFoundException;
import me.bajmo.kanbaniser.services.tasks.exceptions.UserNotFoundException;
import me.bajmo.kanbaniser.services.tasks.models.TaskDTO;
import me.bajmo.kanbaniser.services.tasks.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class TaskController {

    private final TaskService taskService;
    private final TaskConverter taskConverter;

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody TaskDTO taskDTO) {
        taskService.saveTask(taskDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        try {
            Task task = taskService.getTaskRepository().findById(id)
                    .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found!"));
            TaskDTO taskDTO = taskConverter.convertTaskToDTO(task);
            return ResponseEntity.ok(taskDTO);
        } catch (TaskNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        try {
            List<Task> tasks = taskService.getTaskRepository().findAll();
            List<TaskDTO> taskDTOs = tasks.stream()
                    .map(taskConverter::convertTaskToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(taskDTOs);
        } catch (BoardNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody TaskDTO updatedTaskDTO) {
        try {
            taskService.updateTask(id, updatedTaskDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (TaskNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TaskNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PostMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<Void> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId) {
        try {
            taskService.assignTaskToUser(taskId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (TaskNotFoundException | UserNotFoundException e) {
            // Rethrow the caught exceptions
            throw e;
        }
    }

}
