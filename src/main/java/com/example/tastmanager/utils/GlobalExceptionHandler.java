package com.example.tastmanager.utils;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException &&
                ex.getMessage().contains("java.util.Date")) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid date format. Expected format: yyyy-MM-dd");
        }

        return ResponseEntity
                .badRequest()
                .body("Malformed request. Please check your input.");
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<String> handleParseError(ParseException ex) {
        return ResponseEntity.badRequest().body("Invalid date format. Use yyyy-MM-dd.");
    }
}
