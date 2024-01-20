package me.bajmo.kanbaniser.services.boards.exceptions;

public class BoardNotFoundException extends RuntimeException {

    public BoardNotFoundException(String message) {
        super(message);
    }

}