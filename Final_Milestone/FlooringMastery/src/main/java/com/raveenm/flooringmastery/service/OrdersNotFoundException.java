/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.service;

/**
 *
 * @author mathairm
 */
public class OrdersNotFoundException extends Exception {
     public OrdersNotFoundException(String message) {
        super(message);
    }

    public OrdersNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
