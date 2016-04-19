package com.ckgj.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN)
public class MyForbiddenException extends Exception {
    public MyForbiddenException(String msg) {
        super(msg);
    }
}

