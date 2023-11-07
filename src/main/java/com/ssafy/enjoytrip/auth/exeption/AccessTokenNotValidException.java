package com.ssafy.enjoytrip.auth.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccessTokenNotValidException extends RuntimeException{
    public AccessTokenNotValidException(String message) {
        super(message);
    }
}
