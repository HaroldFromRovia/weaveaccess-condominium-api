package com.waveaccess.condominiumapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotAvailableException extends RuntimeException{
    public ResourceNotAvailableException(String message) {
        super(message);
    }
}
