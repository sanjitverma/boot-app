package com.example.verma.bootapp.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by SANJIT on 15/11/17.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ReaderExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError error = new ApiError();
        error.setErrorMessage("Malformed json request");
        error.setStatus(status);
        return new ResponseEntity<Object>(error, error.getStatus());
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setErrorMessage(ex.getMessage());
        apiError.setStatus(status);
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        return new ResponseEntity<Object>(apiError, apiError.getStatus());
    }
}
