package me.bajmo.kanbaniser.services.tasks.repositories;

import me.bajmo.kanbaniser.services.tasks.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
