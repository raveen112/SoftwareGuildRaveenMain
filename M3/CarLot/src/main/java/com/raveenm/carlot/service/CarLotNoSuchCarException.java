/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.carlot.service;

/**
 *
 * @author ravee
 */
public class CarLotNoSuchCarException extends Exception {
    
    public CarLotNoSuchCarException(String message){
        super(message);
    }
    
    public CarLotNoSuchCarException(String message, Throwable cause){
        super(message, cause);
    }
}
