/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
/**
 *
 * @author ravee
 */
@ControllerAdvice
@RestController
public class GuessTheNumberExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CONSTRAINT_MESSAGE = "Could not log game/round. "
            + "Please ensure the right REST message is chosen and try again.";

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Error> handleSqlExcpetion(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        Error err = new Error();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);

    }

}
