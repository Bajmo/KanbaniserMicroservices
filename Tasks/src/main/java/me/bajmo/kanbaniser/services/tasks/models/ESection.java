package me.bajmo.kanbaniser.services.tasks.models;

import lombok.Getter;

@Getter
public enum ESection {
    ToDo("To do"),
    Doing("Doing"),
    Done("Done");

    private final String value;

    ESection(String value) {
        this.value = value;
    }

}
