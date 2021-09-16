/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.exercises;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class InterestCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("How much do you want to invest? ");
        int principal = Integer.parseInt(sc.nextLine());
        System.out.println("How many years are you investing? ");
        int time = Integer.parseInt(sc.nextLine());
        System.out.println("What is the annual intrest rate? ");
        int rate = Integer.parseInt(sc.nextLine());
        
        System.out.println("Calculating: ");
        
        for(int i=1; i<=time; i++)
        {
            int profit = 0;
            int earned = 0;
            int principalgain = principal+profit;
            System.out.println("Year " + i + ": ");
            System.out.println("Began with " + principalgain);
            earned = principal * rate/100;
            profit = principal + earned; 
            System.out.println("Earned " + earned);
            System.out.println("Ended with "+ profit);
            System.out.println("\n");
        }
        
        
    }
    
}
