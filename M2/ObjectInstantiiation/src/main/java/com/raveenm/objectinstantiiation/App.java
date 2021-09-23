/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.objectinstantiiation;

/**
 *
 * @author ravee
 */
public class App {
    public static void main(String[] args) {
        
        Adder myAdder = new Adder();
        
        int sum = myAdder.add(5, 4);
        
        System.out.println("The sum is " + sum);
        
   }
    
           
}
    
    
