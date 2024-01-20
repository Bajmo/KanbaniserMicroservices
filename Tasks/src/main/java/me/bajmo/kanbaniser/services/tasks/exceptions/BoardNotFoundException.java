package me.bajmo.kanbaniser.services.tasks.exceptions;

public class BoardNotFoundException extends RuntimeException {

    public BoardNotFoundException(String message) {
        super(message);
    }

}