package com.awad.anthony.springsecurityjwt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class JwtErrnException extends RuntimeException {
    public JwtErrnException(String message) {
        super(message);
    }

    public JwtErrnException(String message, Throwable cause) {
        super(message, cause);
    }
}