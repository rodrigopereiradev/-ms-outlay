package com.rodrigo.msuser.exceptions;

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

    @ExceptionHandler(UnexpectedErrorException.class)
    public ResponseEntity<Error> handleUnexpectedErrorException( UnexpectedErrorException exception) {
        logger.error(exception.getMessage());
        Error error = getError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Error> handleResourceAlreadyExistsException(ResourceAlreadyExistsException exception) {
        logger.error(exception.getMessage());
        Error error = getError(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Error error = getError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(InvalidArgumentsException.class)
    public ResponseEntity<Error> handleInvalidArgumentsException(InvalidArgumentsException exception) {
        Error error;
        if (exception.getMessages().isEmpty())
            error = getError(HttpStatus.BAD_REQUEST, exception.getMessage());
        else
            error = new Error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), exception.getMessages());

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
