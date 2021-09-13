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
public class HealthyHearts {
    public static void main(String[] args) {
        int age = 0;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What is your age? ");
        age = Integer.parseInt(sc.nextLine());
        
        // max heart rate is 220 - age ;
        //target heart rate zone is 50% of max - 85% of max
        
        // max heart rate
        
        int maxHR = 220-age;
        System.out.println("Max heart rate should be "+ maxHR);
        
        
        
        int maxRange = maxHR *85/100;
        int minRange = maxHR *50/100;
        System.out.println("Your target HR zone is "+ minRange + " - " + maxRange + " beats per minute" );
        
    }
}
