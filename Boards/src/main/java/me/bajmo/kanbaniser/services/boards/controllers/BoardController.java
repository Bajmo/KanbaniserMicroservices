package me.bajmo.kanbaniser.services.boards.controllers;

import lombok.AllArgsConstructor;
import me.bajmo.kanbaniser.services.boards.converters.BoardConverter;
import me.bajmo.kanbaniser.services.boards.entities.Board;
import me.bajmo.kanbaniser.services.boards.exceptions.BoardNotFoundException;
import me.bajmo.kanbaniser.services.boards.exceptions.TaskNotFoundException;
import me.bajmo.kanbaniser.services.boards.models.BoardDTO;
import me.bajmo.kanbaniser.services.boards.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/boards")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class BoardController {

    private final BoardService boardService;
    private final BoardConverter boardConverter;

    @PostMapping
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO) {
        try {
            Board board = boardService.saveBoard(boardDTO);
            return ResponseEntity.ok(boardConverter.convertBoardToDTO(board));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        try {
            List<Board> boards = boardService.getBoardRepository().findAll();
            List<BoardDTO> boardDTOs = boards.stream()
                    .map(boardConverter::convertBoardToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(boardDTOs);
        } catch (BoardNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoardById(@PathVariable Long id) {
        try {
            Optional<Board> boardOptional = boardService.getBoardRepository().findById(id);
            if (boardOptional.isPresent()) {
                Board board = boardOptional.get();
                return ResponseEntity.ok(boardConverter.convertBoardToDTO(board));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBoard(@PathVariable Long id, @RequestBody BoardDTO updatedBoardDTO) {
        try {
            boardService.updateBoard(id, updatedBoardDTO);
            return ResponseEntity.ok().build();
        } catch (BoardNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        try {
            boardService.deleteBoard(id);
            return ResponseEntity.noContent().build();
        } catch (BoardNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{boardId}/tasks/{taskId}")
    public ResponseEntity<Void> addTaskToBoard(@PathVariable Long boardId, @PathVariable Long taskId) {
        try {
            boardService.addTaskToBoard(taskId, boardId);
            return ResponseEntity.ok().build();
        } catch (TaskNotFoundException | BoardNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{boardId}/tasks/{taskId}")
    public ResponseEntity<Void> removeTaskFromBoard(@PathVariable Long boardId, @PathVariable Long taskId) {
        try {
            boardService.removeTaskFromBoard(taskId, boardId);
            return ResponseEntity.ok().build();
        } catch (TaskNotFoundException | BoardNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
