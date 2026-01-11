package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error(ex.getMessage()));
    }

    @ExceptionHandler(BookNotFound.class)
    public ResponseEntity<?> handleBookNotFound(BookNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error(ex.getMessage()));
    }

    @ExceptionHandler(BookAlreadyBorrowed.class)
    public ResponseEntity<?> handleAlreadyBorrowed(BookAlreadyBorrowed ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error(ex.getMessage()));
    }

    @ExceptionHandler(NoCopiesAvailable.class)
    public ResponseEntity<?> handleNoCopies(NoCopiesAvailable ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error(ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(error(ex.getMessage()));
    }

    private Map<String, Object> error(String message) {
        return Map.of(
                "success", false,
                "message", message,
                "timestamp", LocalDateTime.now()
        );
    }
}

