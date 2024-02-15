package com.feras.task.management.exception.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ExceptionUtil {
    public static ResponseEntity<Map<String, String>> prepareResponseBody(Exception exception, HttpStatus httpStatus) {
        Map<String, String> response = new HashMap<>();

        response.put("timestamp", LocalDateTime.now().toString());
        response.put("message", exception.getMessage());

        return new ResponseEntity<>(response, httpStatus);
    }
}
