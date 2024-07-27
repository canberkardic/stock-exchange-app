package com.ardic.stockexchangeapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;

import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {



    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.info("Illegal argument exception occurred: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponseBuilder.builder()
                .date(LocalDateTime.now())
                .message("Illegal Argument Error:")
                .description(List.of(ex.getMessage()))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<?> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        log.info("Method not supported exception occurred: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponseBuilder.builder()
                .date(LocalDateTime.now())
                .message("Method Not Supported:")
                .description(List.of(ex.getMessage()))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        log.info("Exception occurred: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponseBuilder.builder()
                .date(LocalDateTime.now())
                .message("An unexpected error occurred: ")
                .description(List.of(ex.getMessage()))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.info("Error on validation: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponseBuilder.builder()
                .date(LocalDateTime.now())
                .message("Error on validation:")
                .description(ex.getBindingResult().getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList()))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidBodyException(HttpMessageNotReadableException e) {
        log.info("HTTP body not readable: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponseBuilder.builder()
                .date(LocalDateTime.now())
                .message("Http body is not valid")
                .description(List.of(e.getMessage()))
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handleContentTypeException(HttpMediaTypeNotSupportedException e) {
        log.info("Invalid media type:", e.getMessage());
        return new ResponseEntity<>(ErrorResponseBuilder.builder()
                .date(LocalDateTime.now())
                .message("Invalid media")
                .description(List.of(e.getMessage()))
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StockAlreadyExistsInExchangeException.class)
    public ResponseEntity<?> handleStockAlreadyExistsInExchangeException(StockAlreadyExistsInExchangeException ex) {
        log.info("Stock already exists in exchange exception occurred: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponseBuilder.builder()
                .date(LocalDateTime.now())
                .message("Conflict : Stock already exists in the exchange")
                .description(List.of(ex.getMessage()))
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StockAlreadyExistsException.class)
    public ResponseEntity<?> handleStockAlreadyExistsException(StockAlreadyExistsException ex) {
        log.info("Stock already exists exception occurred: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponseBuilder.builder()
                .date(LocalDateTime.now())
                .message("Conflict : Stock already exists")
                .description(List.of(ex.getMessage()))
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StockExchangeNotFoundException.class)
    public ResponseEntity<?> handleStockExchangeNotFoundException(StockExchangeNotFoundException ex) {
        log.info("Stock exchange not found exception occurred: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponseBuilder.builder()
                .date(LocalDateTime.now())
                .message("Stock exchange not found")
                .description(List.of(ex.getMessage()))
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<?> handleStockNotFoundException(StockNotFoundException ex) {
        log.info("Stock not found exception occurred: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponseBuilder.builder()
                .date(LocalDateTime.now())
                .message("Stock not found")
                .description(List.of(ex.getMessage()))
                .build(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
        log.info("Access denied exception occurred: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponseBuilder.builder()
                .date(LocalDateTime.now())
                .message("Access denied for the current user!")
                .description(List.of(ex.getMessage()))
                .build(), HttpStatus.FORBIDDEN);
    }

}

