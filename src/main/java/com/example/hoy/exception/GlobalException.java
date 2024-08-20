package com.example.hoy.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler
    ResponseEntity <?> CustomExceptionHandler(CustomException customException) {
        System.out.println("From Advice");
        return new ResponseEntity<>(customException.getMessage(), HttpStatus.OK);
    }
}
