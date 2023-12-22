package com.ssafy.enjoytrip.attraction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AttractionNotFoundException extends RuntimeException{
    public AttractionNotFoundException(String message) {
        super(message);
    }
}
