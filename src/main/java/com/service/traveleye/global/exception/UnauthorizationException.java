package com.service.traveleye.global.exception;

import lombok.Getter;

@Getter
public class UnauthorizationException extends RuntimeException {
    public UnauthorizationException(String message){
        super(message);
    }
}