package org.example.exceptions;

public class NoCopiesAvailable extends RuntimeException {
    public NoCopiesAvailable(String message) {
        super(message);
    }
}
