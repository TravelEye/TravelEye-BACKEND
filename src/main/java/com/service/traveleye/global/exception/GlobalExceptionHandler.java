package com.service.traveleye.global.exception;

import com.service.traveleye.global.dto.DataResDTO;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public DataResDTO<?> handle(BadRequestException e){
        return DataResDTO.builder().status(400).message(e.getMessage()).build();
    }
    @ExceptionHandler(UnauthorizationException.class)
    public DataResDTO<?> handle(UnauthorizationException e){
        return DataResDTO.builder().status(401).message(e.getMessage()).build();
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public DataResDTO<?> handle(IllegalArgumentException e){
        return DataResDTO.builder().status(400).message(e.getMessage()).build();
    }
    @ExceptionHandler(NotFoundException.class)
    public DataResDTO<?> handle(NotFoundException e){
        return DataResDTO.builder().status(404).message(e.getMessage()).build();
    }
    @ExceptionHandler(IOException.class)
    public DataResDTO<?> handle(IOException e){
        return DataResDTO.builder().status(500).message(e.getMessage()).build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DataResDTO<?> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        return DataResDTO.builder().status(400).message(exception.getMessage()).build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public DataResDTO<?> handleMethodArgumentTypeNotMismatch(MethodArgumentTypeMismatchException exception) {
        return DataResDTO.builder().status(400).message(exception.getMessage()).build();
    }
    @ExceptionHandler(UnsupportedAudioFileException.class)
    public DataResDTO<?> handleUnSupportedAudioFileException(UnsupportedAudioFileException exception) {
        return DataResDTO.builder().status(400).message(exception.getMessage()).build();
    }
    @ExceptionHandler(RuntimeException.class)
    public DataResDTO<?> handleUnSupportedAudioFileException(RuntimeException exception) {
        return DataResDTO.builder().status(500).message("unexpected Exception").build();
    }
}

