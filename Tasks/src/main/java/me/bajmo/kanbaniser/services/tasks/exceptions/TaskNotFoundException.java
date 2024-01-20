package me.bajmo.kanbaniser.services.tasks.exceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message) { super(message); }

}
