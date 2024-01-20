package me.bajmo.kanbaniser.services.tasks.converters;

import me.bajmo.kanbaniser.services.tasks.entities.Task;
import me.bajmo.kanbaniser.services.tasks.models.TaskDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {

    public TaskDTO convertTaskToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskDTOFromTask(task);
        return taskDTO;
    }

    public Task convertDTOToTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTaskFromTaskDTO(taskDTO);
        return task;
    }

}
