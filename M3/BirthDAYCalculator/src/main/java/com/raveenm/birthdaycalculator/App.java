/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.birthdaycalculator;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class App {
    public static void main(String[] args) {
        
        // birthday input
        
        UserIO io = new UserIOConsoleImpl();
        
        String bDay = io.readString("Enter your birthday (MM-dd-yyy): ");
        
        // Day you were born
        LocalDate ld = LocalDate.now();
        
        // Day in the current year
        
        // Difference between date entered and current date
        
        // Difference between birthday and today 
        
    }
}
