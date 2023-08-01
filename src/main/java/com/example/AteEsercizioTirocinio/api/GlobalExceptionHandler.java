package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.exceptions.BadRequestException;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: " + e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404: " + e.getMessage());
    }
}