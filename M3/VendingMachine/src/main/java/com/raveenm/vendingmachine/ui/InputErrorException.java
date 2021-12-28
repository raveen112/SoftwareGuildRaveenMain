/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.ui;

/**
 *
 * @author ravee
 */
public class InputErrorException extends Exception {
     public InputErrorException(String message) {
            super(message);
        }
    public  InputErrorException(String message, Throwable cause) {
            super(message, cause);
}
}
