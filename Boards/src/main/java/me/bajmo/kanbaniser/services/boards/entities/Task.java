package me.bajmo.kanbaniser.services.boards.entities;

import jakarta.persistence.*;
import lombok.*;
import me.bajmo.kanbaniser.services.boards.models.TaskDTO;
import me.bajmo.kanbaniser.services.boards.models.ESection;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "createdBy_id", nullable = false)
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
    private ESection ESection;

    public void setTaskFromTaskDTO(TaskDTO taskDTO) {
        this.id = taskDTO.getId();
        this.title = taskDTO.getTitle();
        this.description = taskDTO.getDescription();
        this.createdAt = taskDTO.getCreatedAt();

        // Map createdBy if not null
        if (taskDTO.getCreatedByUserId() != null) {
            this.createdBy = new User();
            this.createdBy.setId(taskDTO.getCreatedByUserId());
        } else {
            this.createdBy = null; // Handle the case where createdBy is null or not provided in DTO
        }

        // Map board if not null
        if (taskDTO.getBoardId() != null) {
            this.board = new Board();
            this.board.setId(taskDTO.getBoardId());
        } else {
            this.board = null; // Handle the case where board is null or not provided in DTO
        }

        // Map ESection if not null
        this.ESection = (taskDTO.getSection() != null) ? ESection.valueOf(taskDTO.getSection()) : null;
        // Add any additional mapping logic based on your entity structure
    }

}
