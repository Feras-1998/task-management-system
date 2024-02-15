package com.feras.task.management.exception.common;

import com.feras.task.management.exception.common.exceptions.InvalidDataException;
import com.feras.task.management.exception.util.ExceptionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Map<String, String>> handleInvalidDataException(InvalidDataException exception) {
        return ExceptionUtil.prepareResponseBody(exception, HttpStatus.BAD_REQUEST);
    }
}
