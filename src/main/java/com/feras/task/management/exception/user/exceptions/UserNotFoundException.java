package com.feras.task.management.exception.user.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(String.format("%s username not found!!!", username));
    }
}
