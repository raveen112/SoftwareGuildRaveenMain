/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.carlot.dao;

/**
 *
 * @author ravee
 */
public class CarLotPersistenceException extends Exception {

    public CarLotPersistenceException(String message) {
        super(message);
    }

    public CarLotPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
