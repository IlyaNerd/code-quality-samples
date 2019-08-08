package com.itechart.error.handling.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice(assignableTypes = PersonController.class)
public class PersonExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("", ex);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, "Error: " + ex.getMessage(), null,
                HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex ,WebRequest webRequest) {
        return handleExceptionInternal(ex, ErrorPayload.fromConstraintViolations(ex.getConstraintViolations()), null,
                HttpStatus.BAD_REQUEST, webRequest);
    }
}
