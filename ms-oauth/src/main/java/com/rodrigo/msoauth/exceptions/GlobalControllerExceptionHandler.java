package com.rodrigo.msoauth.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> unknownException(Exception exception) {
        logger.error(exception.getMessage());
        Error error = getError(HttpStatus.INTERNAL_SERVER_ERROR, "An Unexpected error has occurred.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Error error = getError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        return ResponseEntity.badRequest().body(error);
    }

    private Error getError(HttpStatus status, String message) {
        return new Error(
                status.value(),
                status.name(),
                message
        );
    }

}
