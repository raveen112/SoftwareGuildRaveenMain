/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.simplecalcalculator;

/**
 *
 * @author ravee
 */
public interface SimpleImpl {
    
    
    void print(String message);

    String readString(String prompt);

    int readInt(String prompt);

    int add(int a, int b);
    
    int sub(int a, int b);
    
    int multiply(int a, int b);
    
    int divide(int a, int b);
    }

