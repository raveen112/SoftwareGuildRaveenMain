/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.raveenm.flooringmastery.service.*;

/**
 *
 * @author ravee
 */
public class InsufficientSquareFootageException extends Exception {

    public InsufficientSquareFootageException(String message) {
        super(message);
    }

    public InsufficientSquareFootageException(String message, Throwable cause) {
        super(message, cause);
    }
}
