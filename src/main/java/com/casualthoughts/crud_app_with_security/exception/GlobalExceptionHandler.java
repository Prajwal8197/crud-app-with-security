package com.casualthoughts.crud_app_with_security.exception;

import com.casualthoughts.crud_app_with_security.dto.GenericApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GenericApiResponse<?>> handleHttpMessageNotReadableException(
            org.springframework.http.converter.HttpMessageNotReadableException ex) {
        String errorMessage = "Malformed JSON request or unknown fields provided.";
        return GenericApiResponse.error(errorMessage, HttpStatus.BAD_REQUEST.value(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map errors = new HashMap();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );
        return GenericApiResponse.error("Validation Failed", HttpStatus.BAD_REQUEST.value(), errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericApiResponse<?>> handleInternalServerError(Exception ex) {
        // logger.error("Internal Server Error: ", ex);
        return GenericApiResponse.error("An unexpected error has occurred", HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
    }


}

