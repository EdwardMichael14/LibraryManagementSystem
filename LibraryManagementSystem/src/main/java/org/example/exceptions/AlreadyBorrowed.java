package org.example.exceptions;

public class AlreadyBorrowed extends RuntimeException {
    public AlreadyBorrowed(String message) {
        super(message);
    }
}
