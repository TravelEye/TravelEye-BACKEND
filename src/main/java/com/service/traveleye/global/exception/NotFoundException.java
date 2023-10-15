package com.service.traveleye.global.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
