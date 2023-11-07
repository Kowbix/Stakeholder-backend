package com.example.Stakeholder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MatchNorFoundException extends RuntimeException{

    public MatchNorFoundException(String message) {
        super(message);
    }
}
