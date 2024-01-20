package me.bajmo.kanbaniser.services.tasks.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.bajmo.kanbaniser.services.tasks.converters.TaskConverter;
import me.bajmo.kanbaniser.services.tasks.entities.Task;
import me.bajmo.kanbaniser.services.tasks.entities.User;
import me.bajmo.kanbaniser.services.tasks.exceptions.TaskNotFoundException;
import me.bajmo.kanbaniser.services.tasks.exceptions.UserNotFoundException;
import me.bajmo.kanbaniser.services.tasks.models.TaskDTO;
import me.bajmo.kanbaniser.services.tasks.repositories.TaskRepository;
import me.bajmo.kanbaniser.services.tasks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final UserRepository userRepository;

    public void saveTask(TaskDTO taskDTO) {
        Task task = taskConverter.convertDTOToTask(taskDTO);
        taskRepository.save(task);
    }

    public void deleteTask(Long id) throws TaskNotFoundException {
        taskRepository.deleteById(id);
    }

    public void updateTask(Long id, TaskDTO updatedTaskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found!"));
        task = taskConverter.convertDTOToTask(updatedTaskDTO);
        taskRepository.save(task);
    }

    @Transactional
    public void assignTaskToUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found!"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found!"));
        task.setCreatedBy(user);
        taskRepository.save(task);
    }

    @Transactional
    public void unassignTaskFromUser(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found!"));
        task.setCreatedBy(null);
        taskRepository.save(task);
    }

    public JpaRepository<Task, Long> getTaskRepository() {
        return this.taskRepository;
    }

}
