package me.bajmo.kanbaniser.services.tasks.converters;

import me.bajmo.kanbaniser.services.tasks.entities.Board;
import me.bajmo.kanbaniser.services.tasks.models.BoardDTO;
import me.bajmo.kanbaniser.services.tasks.repositories.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BoardConverter {

    private final TaskRepository taskRepository;

    public BoardConverter(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public BoardDTO convertBoardToDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardDTOFromBoard(board);
        return boardDTO;
    }

    public Board convertDTOToBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setId(boardDTO.getId());
        board.setTitle(boardDTO.getTitle());
        board.setDescription(boardDTO.getDescription());
        return board;
    }


}
