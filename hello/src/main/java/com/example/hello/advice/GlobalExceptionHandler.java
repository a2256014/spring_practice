package com.example.hello.advice;

import com.example.hello.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity authException(){
        log.info("globalExceptionHandler AuthException");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
