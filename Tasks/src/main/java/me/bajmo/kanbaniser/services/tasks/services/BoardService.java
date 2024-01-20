package me.bajmo.kanbaniser.services.tasks.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.bajmo.kanbaniser.services.tasks.converters.BoardConverter;
import me.bajmo.kanbaniser.services.tasks.entities.Board;
import me.bajmo.kanbaniser.services.tasks.entities.Task;
import me.bajmo.kanbaniser.services.tasks.exceptions.BoardNotFoundException;
import me.bajmo.kanbaniser.services.tasks.exceptions.TaskNotFoundException;
import me.bajmo.kanbaniser.services.tasks.models.BoardDTO;
import me.bajmo.kanbaniser.services.tasks.repositories.BoardRepository;
import me.bajmo.kanbaniser.services.tasks.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class BoardService {

    private final BoardRepository boardRepository;
    private final TaskRepository taskRepository;
    private final BoardConverter boardConverter;

    public Board saveBoard(BoardDTO boardDTO) {
        // Convert DTO to Board entity
        Board board = boardConverter.convertDTOToBoard(boardDTO);

        // Fetch existing tasks and associate them with the board
        List<Task> existingTasks = boardDTO.getTasks().stream()
                .map(taskDTO -> taskRepository.findById(taskDTO.getId())
                        .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskDTO.getId())))
                .collect(Collectors.toList());
        board.setTasks(existingTasks);

        // Save the board to the database
        boardRepository.save(board);

        return board;
    }


    public void deleteBoard(Long id) throws BoardNotFoundException {
        boardRepository.deleteById(id);
    }

    public void updateBoard(Long id, BoardDTO updatedBoardDTO) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("Board with id " + id + " not found!"));

        // Update board details
        board.setTitle(updatedBoardDTO.getTitle());
        board.setDescription(updatedBoardDTO.getDescription());

        // Save the updated board
        boardRepository.save(board);
    }


    @Transactional
    public void addTaskToBoard(Long taskId, Long boardId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found!"));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException("Board with id " + boardId + " not found!"));
        board.getTasks().add(task);
        boardRepository.save(board);
    }

    @Transactional
    public void removeTaskFromBoard(Long taskId, Long boardId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found!"));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException("Board with id " + boardId + " not found!"));
        board.getTasks().remove(task);
        boardRepository.save(board);
    }

    public JpaRepository<Board, Long> getBoardRepository() {
        return this.boardRepository;
    }

}
