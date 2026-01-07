package org.example.exceptions;

public class BookAlreadyBorrowed extends RuntimeException {
    public BookAlreadyBorrowed(String message) {
        super(message);
    }
}
