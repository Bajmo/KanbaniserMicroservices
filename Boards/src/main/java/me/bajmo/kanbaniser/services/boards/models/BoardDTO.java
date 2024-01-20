package me.bajmo.kanbaniser.services.boards.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.bajmo.kanbaniser.services.boards.entities.Board;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long id;
    @NotBlank(message = "Board title is required")
    @Size(max = 50, message = "Board title must be at most 50 characters")
    private String title;
    @Size(max = 200, message = "Board description must be at most 200 characters")
    private String description;
    private List<TaskDTO> tasks;

    public void setBoardDTOFromBoard(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        // Map the list of Task entities to TaskDTOs
        this.tasks = board.getTasks().stream()
                .map(task -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setId(task.getId());
                    taskDTO.setTitle(task.getTitle());
                    taskDTO.setDescription(task.getDescription());
                    taskDTO.setCreatedAt(task.getCreatedAt());
                    taskDTO.setCreatedByUserId(task.getCreatedBy().getId());
                    taskDTO.setBoardId((task.getBoard() != null) ? task.getBoard().getId() : null);
                    taskDTO.setSection((task.getESection() != null) ? task.getESection().name() : null);
                    return taskDTO;
                })
                .collect(Collectors.toList());
    }

}
