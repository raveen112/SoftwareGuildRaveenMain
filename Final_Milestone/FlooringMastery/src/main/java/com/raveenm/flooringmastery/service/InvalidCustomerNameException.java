/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.service;

/**
 *
 * @author ravee
 */
public class InvalidCustomerNameException extends Exception {

    public InvalidCustomerNameException(String message) {
        super(message);
    }

    public InvalidCustomerNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
