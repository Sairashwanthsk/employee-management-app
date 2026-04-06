package com.ems.empmanagamentapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashMap;
import java.util.Map;



@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(ResourceNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("status", "error");;
        error.put("message", "Resource not found: " + ex.getMessage());
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("status", "error");
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        error.put("message", "Validation failed: " + message);
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleGeneric(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("status", "error");
        error.put("message", "An unexpected error occurred: " + ex.getMessage());
        return error;
    }
}