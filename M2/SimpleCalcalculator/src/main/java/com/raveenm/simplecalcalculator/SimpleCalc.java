/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.simplecalcalculator;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class SimpleCalc implements SimpleImpl {
    
    
    Scanner userInput = new Scanner(System.in);
   
    
    @Override
    public void print(String message){
        System.out.println(message);
    }
    
    @Override
    public String readString(String prompt){
        System.out.println(prompt);
        return userInput.nextLine();
    }
    
    @Override
    public int readInt(String prompt){
        System.out.println(prompt);
        return userInput.nextInt();
    }
    
    
    @Override
    public int add(int a, int b){
        return a+b;
}
    @Override
    public int sub(int a, int b){
        return a-b;
    }
    @Override
    public int multiply(int a, int b){
        return a*b;
    }
    
    @Override
    public int divide(int a, int b){
        return a/b;
    }
    
}
