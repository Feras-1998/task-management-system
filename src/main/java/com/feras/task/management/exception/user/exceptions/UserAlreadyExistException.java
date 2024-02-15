package com.feras.task.management.exception.user.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("Username is already exist!!!");
    }
}
