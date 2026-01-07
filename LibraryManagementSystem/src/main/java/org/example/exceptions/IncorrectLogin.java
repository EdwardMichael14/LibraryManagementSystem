package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IncorrectLogin extends RuntimeException {
    public IncorrectLogin(String message) {
        super(message);
    }
}
