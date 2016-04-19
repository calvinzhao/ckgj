package com.ckgj.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class MyBadRequestException extends Exception {
    public MyBadRequestException(String msg) {
        super(msg);
    }
}
