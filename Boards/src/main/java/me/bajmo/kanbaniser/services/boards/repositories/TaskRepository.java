package me.bajmo.kanbaniser.services.boards.repositories;

import me.bajmo.kanbaniser.services.boards.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
