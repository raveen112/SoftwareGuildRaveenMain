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
public class App {
    public static void main(String[] args) {
        
        SimpleImpl userIO = new SimpleCalc();
        Scanner userInput = new Scanner(System.in);
        
        int a = userIO.readInt("Enter your first number: ");
        
        int b = userIO.readInt("Enter your second number: ");
        
        
        System.out.println("What would you like to do to the numbers? ");
        int choice = userIO.readInt("1. Add, 2. Subtract, 3. Multiply, 4. Divide ");
        
        switch (choice) {
            case 1:
                {
                    int sum = userIO.add(a, b);
                    System.out.println("The sum is: " + sum);
                    break;
                }
            case 2:
                {
                    int diff = userIO.sub(a, b);
                    System.out.println("The difference is: " + diff);
                    break;
                }
            case 3:
                {
                    int mul = userIO.multiply(a, b);
                    System.out.println("The product is: " + mul);
                    break;
                }
            case 4:
                {
                    int div = userIO.divide(a, b);
                    System.out.println("The quotient is: " + div);
                    break;
                }
            default:
                System.out.println("Invalid Choice!");
                break;
        }
    }
}
