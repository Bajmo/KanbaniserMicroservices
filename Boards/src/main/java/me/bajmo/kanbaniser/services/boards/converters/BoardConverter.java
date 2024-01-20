package me.bajmo.kanbaniser.services.boards.converters;

import me.bajmo.kanbaniser.services.boards.entities.Board;
import me.bajmo.kanbaniser.services.boards.models.BoardDTO;
import me.bajmo.kanbaniser.services.boards.repositories.TaskRepository;
import org.springframework.stereotype.Component;

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
