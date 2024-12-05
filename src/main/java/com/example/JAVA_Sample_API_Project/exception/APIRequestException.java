package com.example.JAVA_Sample_API_Project.exception;

public class APIRequestException extends RuntimeException {

    public APIRequestException(String message) {
        super(message);
    }

    public APIRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
