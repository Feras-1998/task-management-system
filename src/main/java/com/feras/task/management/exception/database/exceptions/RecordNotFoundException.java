package com.feras.task.management.exception.database.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String className, long id) {
        super(String.format("%s with id %s not found", className, id));
    }
}
