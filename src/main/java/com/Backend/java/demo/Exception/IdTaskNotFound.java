package com.Backend.java.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IdTaskNotFound extends RuntimeException {

    public IdTaskNotFound(String message){
        super(message);

    }
}
