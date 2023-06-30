package com.cms.cms.exceptionhandling;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<CustomError> handleApiException(CustomException ex) {
        return new ResponseEntity<>(new CustomError(ex.getStatus(), ex.getMessage(), Instant.now()), ex.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<CustomError> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(new CustomError(HttpStatus.BAD_REQUEST, e.getMessage(), Instant.now()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            stringBuilder.append(error.getDefaultMessage() + "; ");
        }

        String message = new String(stringBuilder);

        CustomError error = new CustomError(HttpStatus.BAD_REQUEST, message, Instant.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
