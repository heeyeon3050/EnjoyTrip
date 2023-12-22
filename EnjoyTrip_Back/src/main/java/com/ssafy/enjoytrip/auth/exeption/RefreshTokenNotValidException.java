package com.ssafy.enjoytrip.auth.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RefreshTokenNotValidException extends RuntimeException{
    public RefreshTokenNotValidException(String message) {
        super(message);
    }
}
