/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.todoapi.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author ravee
 */
// Will assist all controllers in the project
@ControllerAdvice
// serves a HTTP response on behalf of the controller
@RestController
//ResponseEntityExceptionHandler contains a lot of exception handling code. We gain it by extending.
public class ToDoControllerExceptionHandler extends ResponseEntityExceptionHandler{
    
    private static final String CONSTRAINT_MESSAGE = "Could not save your item. " + 
            "Please ensure it is valid and try again.";
    
    // to indicate which exception its handling
    @ExceptionHandler (SQLIntegrityConstraintViolationException.class)
    // each method must accept java exception and WebRequest as parameters and return a ResponseEntity<T>.
    public final ResponseEntity<Error> handleSqlException(
        SQLIntegrityConstraintViolationException ex,
            WebRequest request) {
        
        Error err = new Error();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
