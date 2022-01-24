/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.random;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class HighRoller {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int diceSides; 
        System.out.println("How many sides does your dice have?");
        diceSides = Integer.parseInt(sc.nextLine());
        Random diceRoller = new Random(); 
       
        int rollResult = diceRoller.nextInt(diceSides)+1; 
        System.out.println("TIME TO ROOOOOLLL THE DICE!");
        
        System.out.println("I rolled a "+rollResult);
        
        if(rollResult ==1){
            System.out.println("You rolled a critical failure. OUCH!");
        }
        else
        {
            System.out.println("CRAZY GOOD JOB! CRITICAL ROLL!");
        }
        
        
     
    }
    
}
