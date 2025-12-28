package com.udemy.spring.application.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.udemy.spring.application.services.exceptions.DatabaseException;
import com.udemy.spring.application.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice // indica que essa classe é um tratador global de exceções
public class ResourceExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class) // essa anotação indica qual exception esse método vai tratar
    public ResponseEntity<StandardError> resourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException,
            HttpServletRequest httpServletRequest) {
		
        String errorMessage = "Resource not found";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        
        StandardError standardError = new StandardError(
        		Instant.now(),
                httpStatus.value(), 
                errorMessage, 
                resourceNotFoundException.getMessage(),
                httpServletRequest.getRequestURI());
        
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(DatabaseException.class) // essa anotação indica qual exception esse método vai tratar
    public ResponseEntity<StandardError> dataBaseException(
            DatabaseException databaseException,
            HttpServletRequest httpServletRequest) {
    	
        String errorMessage = "Database error";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        
        StandardError standardError = new StandardError(
        		Instant.now(),
                httpStatus.value(), 
                errorMessage, 
                databaseException.getMessage(),
                httpServletRequest.getRequestURI());
        
        return ResponseEntity.status(httpStatus).body(standardError);
    }
}
