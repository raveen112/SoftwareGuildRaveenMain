/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.adders;

import java.util.Scanner;


public class adder {
    public static void main(String[] args){
        int operand1 = 3;
        int operand2 = 2;
        int sum = 0;
        
        Scanner sc = new Scanner(System.in);
        
        String stringOperand1 = "";
        String stringOperand2 = "";
        
        System.out.println("Please enter the first number to add: ");
        stringOperand1= sc.nextLine();
        
        System.out.println("Please print out the second number to add: ");
        stringOperand2= sc.nextLine();
        
        operand1 = Integer.parseInt(stringOperand1);
        operand2 = Integer.parseInt(stringOperand2);
                
        sum= operand1 + operand2;
        
        System.out.println("The sum is: " + sum);
    }
}
