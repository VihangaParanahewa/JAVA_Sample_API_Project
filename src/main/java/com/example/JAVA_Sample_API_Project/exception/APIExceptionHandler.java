package com.example.JAVA_Sample_API_Project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

/**
 * Global exception handler for the application.
 */
@ControllerAdvice
public class APIExceptionHandler {
    /**
     * Handles any other exceptions and returns a 400 response.
     *
     * @param exception the exception.
     * @return ResponseEntity with a 400 status and error message.
     */
    @ExceptionHandler(APIRequestException.class)
    public ResponseEntity<Object> handleAPIException(Exception exception) {
        APIException apiException = new APIException(
                "An error occurred: " + exception.getMessage(),
                exception,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles NoSuchElementException and returns a 404 response.
     *
     * @param exception the exception.
     * @return ResponseEntity with a 404 status and error message.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(NoSuchElementException exception) {
        APIException apiException = new APIException(
                exception.getMessage(),
                exception,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic runtime exceptions and returns a 500 response.
     *
     * @param exception the exception.
     * @return ResponseEntity with a 500 status and error message.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        APIException apiException = new APIException(
                "An unexpected error occurred: " + exception.getMessage(),
                exception,
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
