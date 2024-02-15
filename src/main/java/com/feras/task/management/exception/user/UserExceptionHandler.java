package com.feras.task.management.exception.user;

import com.feras.task.management.exception.user.exceptions.UserAlreadyExistException;
import com.feras.task.management.exception.user.exceptions.UserNotFoundException;
import com.feras.task.management.exception.util.ExceptionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistException(UserAlreadyExistException exception) {
        return ExceptionUtil.prepareResponseBody(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException exception) {
        return ExceptionUtil.prepareResponseBody(exception, HttpStatus.NOT_FOUND);
    }
}
