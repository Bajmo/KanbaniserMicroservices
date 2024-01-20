package me.bajmo.kanbaniser.services.tasks.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.bajmo.kanbaniser.services.tasks.entities.Task;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;
    @NotBlank(message = "Task title is required")
    @Size(max = 50, message = "Task title must be at most 50 characters")
    private String title;
    @Size(max = 200, message = "Task description must be at most 200 characters")
    private String description;
    private Date createdAt;
    private Long createdByUserId; // Assuming you want to store user ID who created the task
    private Long boardId; // Assuming you want to store board ID for the task
    private String section; // Assuming you want to store the section as a string, adjust if needed

    public void setTaskDTOFromTask(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.createdAt = task.getCreatedAt();
        this.createdByUserId = task.getCreatedBy().getId();
        this.boardId = (task.getBoard() != null) ? task.getBoard().getId() : null;
        this.section = (task.getESection() != null) ? task.getESection().name() : null;
    }

}